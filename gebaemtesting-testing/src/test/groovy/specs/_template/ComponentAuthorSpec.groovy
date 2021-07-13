//TODO: update to match your component path
package specs._template

import spock.lang.IgnoreIf
import spock.lang.Stepwise
import support.ComponentSpec
import support.page.ui.touch.TouchUIEditor

//TODO: remove this for real components
@IgnoreIf({ System.properties.getProperty("geb.env") != "template" })
@Stepwise
//TODO: update to match you component name
class ComponentAuthorSpec extends ComponentSpec {
    //TODO: update to match path to your component page
    String pathPage = "components/content/text"
    String pathSite = "content/gebaemtesting-showcase"
    String language = "au/en"
    //TODO: update to match path to your component instance
    String componentPath = "jcr:content/root/article/par/contentblock1/par/text"

    def setupSpec() {
        loginAsAdmin()
    }

    def cleanupSpec() {
        analyzeLog()
    }

    def "Authoring of Component"() {
        given: "Component has already been inserted"
        def selector = "#default"

        when: "I am on the Component showcase page"
        TouchUIEditor page = waitForTouchUIPage(language)

        then: "The component should be on showcase page"
        waitFor { withFrame(TouchUIEditor.PAGE_FRAME_CONTENT) { $(selector) } }

        and: "All dialogs are closed"
        page.Editor.isDialogOpen(compileComponentPath()) == false
        report("All dialogs are closed")

        and: "I open the dialog box"
        page.Editor.showDialog(compileComponentPath())

        then: "I should be able to see component author dialog"
        page.Editor.isDialogOpen(compileComponentPath()) == true
        report("I should be able to see component author dialog")

        when: "I close the dialog box"
        page.Editor.closeDialog(compileComponentPath())

        then: "I should be able to close component author dialog"
        page.Editor.isDialogOpen(compileComponentPath()) == false
        report("I should be able to close component author dialog")
    }
}