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
class UniqueExperienceScreenshotSpec extends ComponentSpec {
    //TODO: update to match path to your component page
    String pathPage = "pages/about"
    String pathSite = "content/gebaemtesting-showcase"
    String language = "au/en"
    String componentPath = "jcr:content/root/article"

    def setupSpec() {
        loginAsAdmin()
    }

    //TODO: update text to match your page
    @Unroll("Appearance of Unique Experience Page in #viewport.label")
    def "Appearance of Unique Experience Page"() {
        //TODO: update text to match your page
        given: '>the page hierarchy op pages is created as "Pages" > "About"'
        and: '>the component is on the showcase page'
        def selector = "body"
        def prefix = "spec-"

        when: 'I am in the showcase page.'
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "It should match reference image #viewport.width and height: #viewport.height reference image."
        designRefFull(selector, prefix)

        where: "Browser size width: #viewport.width and height: #viewport.height"
        viewport << getViewPorts()
    }
}
