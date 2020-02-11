# CSCI 3601 Lab #2 - JavaScript and Spark Lab

[![Build Status](https://travis-ci.org/UMM-CSci-3601/3601-lab2_client-server.svg?branch=master)](https://travis-ci.org/UMM-CSci-3601/3601-lab2_client-server)

Here you will explore serving up a simple website that you create, 
using a [Java Spark][spark] server. Spark is a micro framework for 
creating web applications in Java. You will be using Spark to create 
the server component (back-end) of your website.

The client component (front-end) of your website will use JavaScript
to allow you to accept and process user input. We will use JUnit to test
the server code and introduce continuous integration using [Travis CI][travis].

Your specific tasks for this lab can be found in the [LABTASKS.md][labtasks] file in this repository.

## Overview of the project

You'll be building parts of a simple to-do list using a
client-server architecture. The server will be able to handle
simple HTTP GET requests, where a client (or a user) can
visit a URL such as `http://localhost:4567/api/users` and the server
will respond with JSON-formatted text (following the [JSend][jsend] 
guidelines) containing
all the users the server knows about, e.g.,
```
{
  "status": "success",
  "data": {
    "users": [
      {
        "_id": "588935f57546a2daea44de7c",
        "name": "Connie Stewart",
        "age": 25,
        "company": "OHMNET",
        "email": "conniestewart@ohmnet.com"
      },
      {
        "_id": "588935f5597715f06f3e8f6c",
        "name": "Lynn Ferguson",
        "age": 25,
        "company": "NIQUENT",
        "email": "lynnferguson@niquent.com"
      },
      {
        "_id": "588935f51c55b55c75a84848",
        "name": "Roseann Roberson",
        "age": 23,
        "company": "GINKLE",
        "email": "roseannroberson@ginkle.com"
      },
      ...
    ]
  }
}
```

The client will be a combination of HTML, CSS, and JavaScript
that runs in the browser and converts user actions (such as
clicking a button) into requests to the server. In a "real"
system you'd want to display the results nicely as part of
the application web interface (like the nicely formatted
list of e-mails in GMail).
To keep this lab simple, however, you'll just display the "raw" JSON
that the client receives from the server.

This lab has two components:

* Implement the desired server functionality
* Implement a simple web client that allows users to 
access that server functionality through HTML forms

The details of both of these components are in [LABTASKS.md](./LABTASKS.md).

## Setup

### Setting up Java in IntelliJ

> You may not need this, especially if you've already used IntelliJ to work on Java projects.
If you're unsure, go ahead and start the process, though, and you can quit if/when you
get to a point where it's clear that you've already done thing.

Open up [IntelliJ IDEA][intellij-idea] and make sure that Java is set up correctly:

* From the welcome screen of IntelliJ, in the lower right corner-ish area, click on Configure.
   * If you're not on the welcome screen because, for example, you have a project open from 
     a previous lab or a different project, choose "File -> Close Project" to close that
     project and get to the welcome screen.
* Choose "Project Defaults"
* Choose "Project Structure"
* In the Project SDK section, there is a dropdown menu for the SDK to select. 
   * If there is no SDK, it will say so in red "<No SDK>". 
     There is a button to the right of that that says "New...". Click that button. 
     In our lab, you can find Java in `/usr/lib/jvm/java`
   * If there is an SDK selected and there's no shout-y red text, then you've probably
     already done this and you can cancel out of all this.

### Importing your project into IntelliJ

From the welcome window in IntelliJ choose "Check out from version control". This lets you
specify the URL of your project, and IntelliJ's internal `git` will then `git clone` your
project into IntelliJ.

After you enter the URL and specify where you'd like IntelliJ to place your clone, you'll
be asked whether you would like create an IntelliJ project for the sources you've 
checked out, **select yes**.

Then, select **Import project from existing model** and select **Gradle**. 
Make sure **Use default gradle wrapper** is selected on the next screen, and click **OK**.
If all goes according to plan, you should then see your project loaded up in IntelliJ waiting
for you to do cool work.

### Install JSONView browser extension

You'll also want the JSONView extension for either Firefox or Chrome installed. 
This will make JSON in the browser look pretty and actually be readable.

* Firefox: [JSONView][jsonview-firefox]
* Chrome: [JSONView][jsonview-chrome]

## Running Your project

We use the [Gradle][gradle] build tool to build and run our web application.
Gradle is a powerful system for defining, managing, and running tasks
that allows us to easily build and test our full web application.

Open the Gradle tool window in IntelliJ by going to: `View -> Tool Windows -> Gradle`. From here, open up the `Tasks` section. Gradle tasks run things like the development server, production build, and tests. Open up the `application` task category and double click `run`.

Your server should now be running on port 4567, the default Spark port. 
Visit it at [http://localhost:4567][local] in your web browser. The
server will continue to run indefinitely until you stop it in
IntelliJ; there's a red square in the upper right of the toolbar
that you can use to stop tasks that you've started with Gradle
in IntelliJ. :bangbang: Make sure you stop your server before you quit
IntelliJ; otherwise you can end up preventing other people from
successfully running their project on this machine later, and that
makes everyone grumpy.

## Testing Your Project

There's very little meaningful logic in the client component of this
project so we're not going to worry about testing the client here.
We'll begin testing the client when we introduce Angular in subsequent 
labs.

The server-side portion of this project will be tested using JUnit.
Server-side tests are located in the `src/test/java` directory.

To run your server-side tests, you can either 

   * Double click the `runServerTests` task under 
     `Tasks -> application` in the Gradle view in IntelliJ, or
   * Type `./gradlew runServerTests` on the command line in the top
     directory of the project., or
   * Right clicking on `java` under `src/test` in the project view
     in IntelliJ, and then choosing `Run All Tests`.
     
This last option is particularly nice because it gives you better
integrated feedback in IntelliJ and the ability to just run single
tests or files of tests when you're trying to debug a complex problem.

## Continuous Integration with Travis CI

[Travis CI][travis] is a Continuous Integration tool that performs 
builds of your project every time you push to GitHub. 
This is very helpful, as it makes keeping track of your testing 
over the lifetime of a project very easy. Having a build/test 
history makes it easy to find where, or when, your project broke,
which makes it a lot easier to figure out _why_ it broke and get
it fixed and building successfully again.

(Any open-source, public project on GitHub can use Travis CI for
free. People normally need to pay for 
the ability to use Travis to build and test private repositories, but
through your GitHub student pack, you get free private builds on 
Travis while you're a student. We won't use that fact in this class,
but it's worth being aware of.)

> Protip: The [GitHub Student pack](https://education.github.com/pack) 
has a ton of really awesome 
stuff in it, including $50 of credit to Digital Ocean! 

We've done the hard part of setting up the [.travis.yml][travis-yml] 
file. You can look at it by clicking the previous link or 
[read about setting up a Java Gradle project with 
Travis][travis-java].

What you need to do:
- Sign into [Travis-CI.org][travis] with your GitHub account.

> Protip: We'd recommend having everyone in your group do this; you'll
  all want to have set up on Travis-CI.org eventually, so best to do
  it now.

> :bangbang: Make sure you go to *.org* and _not_ *.com*. Travis-CI
has both a `.org` and a `.com` version for their free and paid
customers, respectively. You will be a non-paying customer, so you
_have_ to use the `.org` site; if you attempt to use the `.com`
site you won't see any of your projects.

- Select the appropriate org from the list of orgs so that the repo 
for your lab (which was generated by GitHub Classroom) will show up 
in the list of repositories
  - You may need to authorize the application to access your GitHub account
  - Read through the "First Time Here?" instructions
- Click on the "+" on the left side of the page near "My Repositories"
- Find your fork for this lab in the list of repositories.
- Enable continuous integration for it by clicking the big toggle switch beside it
- Then, click on the gear icon (settings) directly next to the previous toggle switch
- From here, you can see various pieces of information and
settings regarding the builds of your project. You don't need
to change anything at the moment, but it's good to see what's
there

Before you can see Travis-CI doing anything you need to make some
change and push it up to GitHub, because pushes to GitHub are what
triggers Travis-CI to wake up and try to build that latest version
of your project.

You could make really _any_ change, but there's a specific change
we _need_ to make, so let's do that. At the top of this README file
is a Travis-CI badge link that ends up (hopefully) displaying as 
that nice green "build passing" badge on our page. At the moment that
link gets the badge for _our_ copy of the project (the one GitHub
Classroom forked for you when you started the lab) and if you don't
update that link you'll be constantly displaying the state of _our_
version instead of _your_ version. 

To fix that, grab the link info from Travis. On the Travis-CI web 
site, click on the `Build Status Image` button to the right of 
the name of the repository.

  - Select "Master" for the Branch.
  - Select "Markdown" for the drop-down.
  - Copy the Markdown it provides.
  - Replace our link in this README with the Markdown that you 
    just got from Travis-CI.
  - Commit & push that change.

The commit and push should trigger a build on Travis, and shortly
you should be able to see that Travis has queued up your project
for building & testing. Actually running the tests can take a few
minutes, especially as the projects get bigger, so be patient.

Your own forked project is now ready for the magic of continuous integration!

## Go do the lab!

Now that you're all set up, you should be ready to head over to [LABTASKS.md](./LABTASKS.md), where
most of the actual work of the lab is described.

## Resources

##### Running in the command line

We include a Gradle wrapper which lets you run gradle tasks from the command line. So, you can run tasks like:

```
./gradlew test
```

##### Handling requests in Spark

- [Using Spark to create APIs in Java][spark-api]
- [Response handling with Spark][spark-response]
- [Spark documentation][spark-documentation]
- [Example of a nicely structured, more complex Spark project][spark-structure]
- [JSend "standard" for JSON responses][jsend]
- [Best practices for REST interface design][rest-best-practices]
- [HTTP Status Codes][status-codes]

[gradle]: https://gradle.org/
[intellij-idea]: https://www.jetbrains.com/idea/
[jasmine]: https://jasmine.github.io/
[jasmine-introduction]: http://jasmine.github.io/2.0/introduction.html
[jsend]: https://github.com/omniti-labs/jsend
[jsonview-chrome]: https://chrome.google.com/webstore/detail/jsonview/chklaanhfefbnpoihckbnefhakgolnmc?hl=en
[jsonview-firefox]: https://addons.mozilla.org/en-us/firefox/addon/jsonview/
[karma]: https://karma-runner.github.io/1.0/index.html
[labtasks]: LABTASKS.md
[local]: http://localhost:4567/
[rest-best-practices]: https://medium.com/@mwaysolutions/10-best-practices-for-better-restful-api-cbe81b06f291
[spark]: http://sparkjava.com/
[spark-api]: http://nordicapis.com/using-spark-to-create-apis-in-java/
[spark-documentation]: http://sparkjava.com/documentation.html
[spark-response]: http://sparkjava.com/documentation.html#response
[spark-structure]: http://sparkjava.com/tutorials/application-structure
[travis]: https://travis-ci.org/
[travis-java]: https://docs.travis-ci.com/user/languages/java/
[travis-yml]: .travis.yml
[status-codes]: https://en.wikipedia.org/wiki/List_of_HTTP_status_codes
