package specs.components.content.text

import spock.lang.IgnoreIf
import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class TextPublishSpec extends ComponentSpec {
    String pathPage = "components/content/text"
    String pathSite = "content/gebaemtesting-showcase"
    String language = "au/en"
    String componentPath = "jcr:content/root/article/par/contentblock1/par/text"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Functionality of Component Variant: Default in #viewport.label")
    def "Functionality of Component Variant: Default"() {
        given: '>the page hierarchy is created as "Components" > "Content" > "Text"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#text1"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        waitForComponent(selector)

        and: "Should have sample rich text"
        assert $(selector).text().trim().startsWith("Heading 1")
        report("Should have sample rich text")

        and: "Has sample table content"
        assert !$("${selector} table").isEmpty()

        and: "Has sample link"
        assert !$("${selector} a").isEmpty()

        where: "Browser size width: #viewport.width and height: #viewport.height"
        viewport << getViewPorts()
    }
}
