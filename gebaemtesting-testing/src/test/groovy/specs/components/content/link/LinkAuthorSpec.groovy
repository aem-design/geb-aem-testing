package specs.components.content.link


import spock.lang.Stepwise
import support.ComponentSpec
import support.page.ui.touch.TouchUIEditor

@Stepwise
class LinkAuthorSpec extends ComponentSpec {
    String pathPage = "components/content/link"
    String pathSite = "content/gebaemtesting-showcase"
    String language = "au/en"
    String componentPath = "jcr:content/root/article/par/contentblock/par/contentblock1/par/link"

    def setupSpec() {
        loginAsAdmin()
    }

    def cleanupSpec() {
        analyzeLog()
    }

    def "Authoring of Component"() {
        given: "Component has already been inserted"
        def selector = "#link1"

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