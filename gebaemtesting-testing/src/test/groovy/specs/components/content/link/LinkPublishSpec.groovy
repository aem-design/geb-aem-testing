package specs.components.content.link


import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class LinkPublishSpec extends ComponentSpec {
    String pathPage = "components/content/link"
    String pathSite = "content/gebaemtesting-showcase"
    String language = "au/en"
    String componentPath = "jcr:content/root/article/par/contentblock/par/contentblock1/par/link"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Functionality of Component Variant: Default in #viewport.label")
    def "Functionality of Component Variant: Default"() {
        given: '>the page hierarchy is created as "Components" > "Content" > "Link"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#link1"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        waitForComponent(selector)

        and: "Should have Sample Link Text"
        assert $(selector).text().trim().contains("Link")
        report("Should have sample Link text")

        where: "Browser size width: #viewport.width and height: #viewport.height"
        viewport << getViewPorts()
    }
}
