//TODO: update to match your component path
package specs._template

import spock.lang.IgnoreIf
import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

//TODO: remove this for real components
@IgnoreIf({ System.properties.getProperty("geb.env") != "template" })
@Stepwise
//TODO: update to match you component name
class ComponentScreenshotSpec extends ComponentSpec {
    //TODO: update to match path to your component page
    String pathPage = "components/content/text"
    String pathSite = "content/gebaemtesting-showcase"
    String language = "au/en"
    //TODO: update to match path to your component instance
    String componentPath = "jcr:content/root/article/par/contentblock1/par/text"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Appearance of Component Variant: Default in #viewport.label")
    def "Appearance of Component Variant: Default"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#default"

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
