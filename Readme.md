# Commercetools platform query template


Template for querying existing [CTP](http://www.commercetools.com/en/) projects using [Cornichon](https://github.com/agourlay/cornichon)

## Configuration

By default the tests target the production API but you can change manually the content of the configuration in ```src/test/resources/reference.conf```.

Warning, the tests require the definition of the ```clientId``` and ```clientSecret``` for your project.

 
## Running the tests

The project contains [sbt-extras](https://github.com/paulp/sbt-extras) so you do not need to install ```sbt```.
  
In the root folder run ```sbt test``` or ```/.sbt test``` to use the sbt-extra script.
  
For more ```sbt``` commands see this [doc](https://github.com/agourlay/cornichon#scalatest-integration)  
 