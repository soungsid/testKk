package com.mycompany.myapp.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.mycompany.myapp.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.mycompany.myapp.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.mycompany.myapp.domain.User.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Authority.class.getName());
            createCache(cm, com.mycompany.myapp.domain.User.class.getName() + ".authorities");
            createCache(cm, com.mycompany.myapp.domain.Tag.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Categorie.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Qcm.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Qcm.class.getName() + ".createBies");
            createCache(cm, com.mycompany.myapp.domain.Qcm.class.getName() + ".idCategories");
            createCache(cm, com.mycompany.myapp.domain.QcmQuestion.class.getName());
            createCache(cm, com.mycompany.myapp.domain.QcmQuestion.class.getName() + ".idQcms");
            createCache(cm, com.mycompany.myapp.domain.QcmQuestionTag.class.getName());
            createCache(cm, com.mycompany.myapp.domain.QcmQuestionTag.class.getName() + ".idQcmQuestions");
            createCache(cm, com.mycompany.myapp.domain.QcmQuestionTag.class.getName() + ".idTags");
            createCache(cm, com.mycompany.myapp.domain.QcmReponse.class.getName());
            createCache(cm, com.mycompany.myapp.domain.QcmReponse.class.getName() + ".idQcmQuestions");
            createCache(cm, com.mycompany.myapp.domain.QcmTest.class.getName());
            createCache(cm, com.mycompany.myapp.domain.QcmTest.class.getName() + ".idQcms");
            createCache(cm, com.mycompany.myapp.domain.QcmTest.class.getName() + ".idUtilisateurs");
            createCache(cm, com.mycompany.myapp.domain.QcmTestResponse.class.getName());
            createCache(cm, com.mycompany.myapp.domain.QcmTestResponse.class.getName() + ".idQcmTests");
            createCache(cm, com.mycompany.myapp.domain.QcmTestResponse.class.getName() + ".idQcmQuestions");
            createCache(cm, com.mycompany.myapp.domain.QcmTestResponse.class.getName() + ".idQcmReponses");
            createCache(cm, com.mycompany.myapp.domain.Utilisateur.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Utilisateur.class.getName() + ".idCountries");
            createCache(cm, com.mycompany.myapp.domain.Utilisateur.class.getName() + ".idSocietes");
            createCache(cm, com.mycompany.myapp.domain.SocieteAbonne.class.getName());
            createCache(cm, com.mycompany.myapp.domain.CarnetAdresse.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Country.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cm.destroyCache(cacheName);
        }
        cm.createCache(cacheName, jcacheConfiguration);
    }

}
