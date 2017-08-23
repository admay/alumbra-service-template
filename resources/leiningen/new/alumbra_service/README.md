# {{name}}

This is an Alumbra based GraphQL service too...

### To get started

First, you'll have to set up a DB.
I suggest Postgres, but you can use whatever you want.
If you don't know how to install and create a database, check out the [postgres tutorial][1].

Once you have the database created, you'll have to do two things;

1. Create the tables
2. Export the database url

#### Creating the tables

A SQL script can be found inside of the resources directory of this project.
Connect to your database and run that SQL script.
It will create for you, two tables that are used by the service.
If you want to add some starting data, now would be a good time.

#### Export the database url

There are a couple of ways we can do this.
For development purposes, I suggest that you export the url connection string as an environment variable.
Having the variable on your $PATH will allow `environ` to find it and use it within the project.
This is an easy development workflow because of it's flexibility, however it's not perfect.
If you terminate your current terminal without some way to maintain the reference in the env, you'll have to do it again.
This is the source of a lot of confusion because, "I already did that, why do I need to do it again?"

Another way to do this, which is _very_ easy, but also insecure, is to add the connection string to a profiles.clj.
This will take the irritation of making sure the reference exists off of your mind, but now you might accidentally push database credentials to a public git repo.
I didn't add a project.clj file in the template because I think this is a bad idea.
You've been warned.


[1]: http://www.postgresqltutorial.com/
