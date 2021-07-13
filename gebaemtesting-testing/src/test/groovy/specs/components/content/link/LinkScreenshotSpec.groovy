package specs.components.content.link


import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class LinkScreenshotSpec extends ComponentSpec {
    String pathPage = "components/content/link"
    String pathSite = "content/gebaemtesting-showcase"
    String language = "au/en"
    String componentPath = "jcr:content/root/article/par/contentblock/par/contentblock1/par/link"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Appearance of Component Variant: Default in #viewport.label")
    def "Appearance of Component Variant: Default"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#link1"

        when: 'I am in the component showcase page'
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        waitForComponent(selector)

        then: "It should match the #viewport.width and height: #viewport.height reference image."
        designRef(selector)

        where: "Browser size width: #viewport.width and height: #viewport.height"
        viewport << getViewPorts()
    }
}
