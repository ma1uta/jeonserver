/*
 * Copyright JeonServer sablintolya@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.ma1uta.jeonserver.standalone.core;

import com.typesafe.config.Config;
import io.github.ma1uta.jeonserver.entity.core.Domain;
import io.github.ma1uta.jeonserver.standalone.BootstrapException;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

/**
 * Domain Service.
 */
@Slf4j
public class DomainService {

    private Map<String, Domain> domains = new HashMap<>();

    @Inject
    private Provider<EntityManager> emProvider;

    @Inject
    private Config config;

    /**
     * Initialize.
     */
    @PostConstruct
    @Transactional
    protected void init() {
        if (!config.hasPath("domains")) {
            throw new BootstrapException("Cannot find configured domains.");
        }

        EntityManager em = emProvider.get();
        Map<String, Domain> storedDomains = new HashMap<>();
        for (Domain domainItem : em.createQuery("select d from Domain d", Domain.class).getResultList()) {
            storedDomains.put(domainItem.getDomain(), domainItem);
        }

        for (Config domainConfig : config.getConfigList("domains")) {
            String name = domainConfig.getString("name");
            log.info("Found domain: {}", name);
            Domain domain = storedDomains.get(name);
            if (domain == null) {
                domain = new Domain();
                domain.setDomain(name);
                log.debug("Save domain.");
                em.persist(domain);
            }
            domains.put(name, domain);
        }
    }

    public Map<String, Domain> getDomains() {
        return Collections.unmodifiableMap(domains);
    }
}
