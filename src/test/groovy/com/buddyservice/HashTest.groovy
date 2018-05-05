package com.buddyservice

import com.buddyservice.auth.IAuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(locations = ["classpath:jdbc-connection-test-context.xml"])
class HashTest extends Specification {

    @Autowired
    public IAuthService authService

    def "test hashing string password to md5"() {
        given: "preparing test data"
            String heslo = "heslo"
        when: "creating md5 hash"
            String md5 = authService.createMD5Hash(heslo)
        then: "asserting hash"
            assert authService.createMD5Hash(heslo) == md5
    }

}
