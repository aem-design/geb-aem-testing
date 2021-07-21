# AEM - AEM Testing Framework

Automated Functional Testing Framework using Docker and Selenium Grid to test AEM Sites.

This will test your specified instance of AEM using Docker container. 

To check which parameters can be used check `test-spec.ps1` for Windows and you can run `./test-spec  --help` to check parameters for Linux/Bash execution.


Test execution will generate sub-directory in the project with test run results.
This is done so that you can run multiple test cycles at the same time with different parameters.  

* `remote-seleniumhub-chrome` - will be named as per driver name passed as parameter `TEST_DRIVER_NAME`
  * `generated-docs` - as the Ascii docs output of the final report
  * `spock-reports` - has the json and Markdown generated results after execution 
  * `surefire-reports` - has the content for report run output
  * `test-classes` - has the compiled tests
  * `test-reports` - has the outputs of the component execution HTML and images


When executing Screenshot test they will be saved in `src/test/screenshots` first time the spec is ran. 
You can commit these files to git, and they will be used as a reference source for next run.  



## Running Tests using Docker Compose

To run tests, run following command in `gebaemtesting-testing` directory:

```
./test-spec
```

## Running Tests using Local Maven

You can test the execution of tests on your local system using following command. This will run tests using local selenium and AEM instances and will execute all tests listed in `test-list` with viewports listed in `test-viewports`: 

```powershell
./test-spec -SILENT -TEST_USING_MAVEN true
```

This will use maven for main execution but will attempt to use Docker for ImageMagic and Asciidoc reports.

## Additional Usage

Using Powershell 7 execute selected tests run following command:

```powershell
.\test-spec.ps1 -TEST_SPECS TextA*
```

On Linux you can do following command:

```bash
./test-spec --tests TextA*
```

Above commands will only execute `TextAuthorSpec`.

Update `test-list` with test you want to run without specifying parameters, this should be a complete list of test you want to run.

```powershell
.\test-spec.ps1 
```

To only test specific viewports specify `TEST_VIEWPORTS` param

```powershell
.\test-spec -TEST_VIEWPORTS "XLG,SM"
```


## Docs

- [Authoring Pages](https://docs.adobe.com/docs/en/aem/6-0/author/page-authoring.html)
- [Authoring - the Environment and Tools](https://docs.adobe.com/docs/en/aem/6-0/author/page-authoring/author-environment-tools.html)
- [Components for Page Authoring](https://docs.adobe.com/docs/en/aem/6-0/author/page-authoring/default-components/editmode.html)


- [Geb Github Projects](https://github.com/geb)
- [Geb Maven Sample](https://github.com/geb/geb-example-maven)
- [Spock and Geb in Action](http://www.slideshare.net/InfoQ/taming-functional-web-testing-with-spock-and-geb)
- [Taming Functional Web Testing with Spock and Geb](http://www.slideshare.net/InfoQ/taming-functional-web-testing-with-spock-and-geb)

## Maven Reports

- [Spock Reports Extension](https://github.com/renatoathaydes/spock-reports)
- [Surefire Reports](http://maven.apache.org/surefire/maven-surefire-report-plugin/usage.html)


## ASCII Doc Reports
Adding screenshots to Test Steps is done by detecting a text string in code block and then adding picking a screenshot to show.
Update the following line in ```spect-template.ad```:

```
    def codeCheckFileGen = /[\t ]*report|designRef|takeScreenshot.*/
```

For multi-screenshot print check following block to ensure columns are printed.

```
    if (filePathFull.endsWith(pathCheckEndImage)) {
        if (filePathFull.startsWith(pathCheckStartSource)) {
            blockScreenshots = blockScreenshots << """ .Source\n"""
        } else if (filePathFull.endsWith(pathCheckEndDiff)) {
            blockScreenshots = blockScreenshots << """ a| .Diff\n"""
        } else if (!filePathFull.startsWith(pathCheckStartSpec)) {
            if (it.files.size() > 1) {
                blockScreenshots = blockScreenshots << """ a| """
            }
            blockScreenshots = blockScreenshots << """ .Current\n"""
        }
```

If HTML report are not showing expected result check base templates in ```spock-reports/.ad``` these are converted to html.

## Driver Specific Parameters


You can pass following variable via command line to specify browserstack parameters.

For more information please see following reference site [https://www.browserstack.com/automate/capabilities](https://www.browserstack.com/automate/capabilities)

| Internal Name                      | Command Line Param                 | Browserstack Variable        |
|------------------------------------|------------------------------------|------------------------------|
| GEB_BROWSERSTACK_USERNAME          | geb.browserstack.username          |                              |
| GEB_BROWSERSTACK_AUTHKEY           | geb.browserstack.authkey           |                              |
| GEB_BROWSERSTACK_SCHEMA            | geb.browserstack.schema            |                              |
| GEB_BROWSERSTACK_HOST              | geb.browserstack.host              |                              |
| GEB_BROWSERSTACK_URL               | "SCHEMA://USERNAME:AUTHKEY@HOST"   |                              |
| GEB_BROWSERSTACK_BROWSER           | geb.browserstack.browser           | browser                      |
| GEB_BROWSERSTACK_BROSWER_VERSION   | geb.browserstack.browserversion    | browser_version              |
| GEB_BROWSERSTACK_BUILD             | geb.browserstack.build             | build                        |
| GEB_BROWSERSTACK_OS                | geb.browserstack.os                | os                           |
| GEB_BROWSERSTACK_OS_VERSION        | geb.browserstack.osversion         | os_version                   |
| GEB_BROWSERSTACK_DEBUG             | geb.browserstack.debug             | browserstack.debug           |
| GEB_BROWSERSTACK_DEVICE            | geb.browserstack.device            | device                       |
| GEB_BROWSERSTACK_REALMOBILE        | geb.browserstack.device            | realMobile                   |
| GEB_BROWSERSTACK_RESOLUTION        | geb.browserstack.resolution        | resolution                   |
| GEB_BROWSERSTACK_LOCAL             | geb.browserstack.local             | browserstack.local           |
| GEB_BROWSERSTACK_LOCALID           | geb.browserstack.localIdentifier   | browserstack.localIdentifier |
| GEB_BROWSERSTACK_PROJECT           | geb.browserstack.project           | project                      |
| GEB_BROWSERSTACK_ACCESPTSSL        | geb.browserstack.acceptSslCerts    | acceptSslCerts               |
| GEB_BROWSERSTACK_PLATFORM          | geb.browserstack.platform          | platform                     |
| GEB_BROWSERSTACK_SELENIUMVERSION   | geb.browserstack.selenium_version  | selenium_version             |
| GEB_BROWSERSTACK_NAME              | geb.browserstack.name              | name                         |
| GEB_BROWSERSTACK_CONSOLE           | geb.browserstack.console           | browserstack.console         |
| GEB_BROWSERSTACK_VIDEO             | geb.browserstack.video             | browserstack.video           |
| GEB_BROWSERSTACK_NETWORKLOGS       | geb.browserstack.networkLogs       | browserstack.networkLogs     |
| GEB_BROWSERSTACK_TIMEZONE          | geb.browserstack.timezone          | browserstack.timezone        |
| GEB_BROWSERSTACK_GEOLOCATION       | geb.browserstack.geoLocation       | browserstack.geoLocation     |
| GEB_BROWSERSTACK_NETWORKSPEED      | geb.browserstack.customNetwork     | browserstack.customNetwork   |
| GEB_BROWSERSTACK_DEVICEORIENTATION | geb.browserstack.deviceOrientation | deviceOrientation            |
