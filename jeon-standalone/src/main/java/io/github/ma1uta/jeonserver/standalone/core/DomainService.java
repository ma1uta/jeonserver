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
import io.github.ma1uta.jeonserver.standalone.configuration.DomainConfiguration;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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

    private static final int DEFAULT_RATE_LIMIT = 500;

    private static final int DEFAULT_FEDERATION_DELAY = 500;

    private static final int DEFAULT_FEDERATION_WORKERS = 5;

    private static final int DEFAULT_PREVIEW_SIZE = 1024 * 1024 * 10;

    private Map<String, DomainConfiguration> domains = new HashMap<>();

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
            domains.put(name, loadDomain(name, domainConfig));
        }

        domains = Collections.unmodifiableMap(domains);
    }

    private DomainConfiguration loadDomain(String domain, Config config) {
        DomainConfiguration domainConfiguration = new DomainConfiguration();
        domainConfiguration.setName(domain);

        DomainConfiguration.Ratelimit ratelimit = new DomainConfiguration.Ratelimit();
        ratelimit.setMessagesPerSecond(getInt(config, "ratelimit.messages_per_second", DEFAULT_RATE_LIMIT));
        domainConfiguration.setRatelimit(ratelimit);

        DomainConfiguration.Federation federation = new DomainConfiguration.Federation();
        federation.setDelay(getInt(config, "federation.delay", DEFAULT_FEDERATION_DELAY));
        federation.setWorkerPool(getInt(config, "federation.workers", DEFAULT_FEDERATION_WORKERS));
        domainConfiguration.setFederation(federation);

        DomainConfiguration.Preview preview = new DomainConfiguration.Preview();
        preview.setMaxSize(getInt(config, "preview.max_size", DEFAULT_PREVIEW_SIZE));
        preview.setBlacklist(getStringList(config, "preview.blacklist"));
        domainConfiguration.setPreview(preview);

        DomainConfiguration.Blacklist blacklist = new DomainConfiguration.Blacklist();
        blacklist.setUsernames(getStringList(config, "blacklist.usernames"));
        blacklist.setServers(getStringList(config, "blacklist.servers"));
        domainConfiguration.setBlacklist(blacklist);

        DomainConfiguration.Turn turn = new DomainConfiguration.Turn();
        turn.setUsername(getString(config, "turn.username", "turn"));
        turn.setPassword(getString(config, "turn.password", "turn"));
        turn.setUris(getStringList(config, "turn.uris"));
        turn.setAllowGuest(getBoolean(config, "turn.allow_guests", false));
        domainConfiguration.setTurn(turn);

        return domainConfiguration;
    }

    private int getInt(Config config, String path, int defaultValue) {
        return config.hasPath(path) ? config.getInt(path) : defaultValue;
    }

    private List<String> getStringList(Config config, String path) {
        return config.hasPath(path) ? config.getStringList(path) : Collections.emptyList();
    }

    private String getString(Config config, String path, String defaultValue) {
        return config.hasPath(path) ? config.getString(path) : defaultValue;
    }

    private boolean getBoolean(Config config, String path, boolean defaultValue) {
        return config.hasPath(path) ? config.getBoolean(path) : defaultValue;
    }

    public Map<String, DomainConfiguration> getDomains() {
        return domains;
    }

    /**
     * Get domain configuration.
     *
     * @param domain domain name.
     * @return domain configuration.
     */
    public DomainConfiguration getDomain(String domain) {
        return domains.get(domain);
    }
}
