package org.hidetake.gradle.swagger.generator.test.openapi3


import org.gradle.testkit.runner.TaskOutcome
import org.hidetake.gradle.swagger.generator.test.GradleProject
import spock.lang.Specification

class CustomTemplateSpec extends Specification {

    final project = new GradleProject(':openapi-v3:custom-template')

    def 'generateSwaggerCode task should generate customized server code'() {
        when:
        def result = project.execute('generateSwaggerCode')

        then:
        result.task(project.absolutePath('generateSwaggerCode')).outcome == TaskOutcome.NO_SOURCE
        result.task(project.absolutePath('generateSwaggerCodePetstore')).outcome == TaskOutcome.SUCCESS
        project.file('build/swagger-code-petstore/index.html').exists()
    }

    def 'generateSwaggerCodePetstoreHelp task should show help'() {
        when:
        def result = project.execute('generateSwaggerCodePetstoreHelp')

        then:
        result.output.contains('CONFIG OPTIONS')
    }

}
