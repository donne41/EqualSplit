# EqualSplit

## Description

A simple tool to share the expenses between friends during a vacation for example.
This is where the idea came from actually.
I came home from a vacation with friends and we all had different expenses we shared.
How will we split this equally? I could just calculate it, but then again, 
  
  "why spend 5 minutes on a task manually when you can spend 6 hours trying to automate it?"



 
## Instructions

This is the backend part of the program, it include only the barebone.

To use this you need to add this in your pom.xml

```
        <dependency>
            <groupId>com.github.donne41</groupId>
            <artifactId>equalsplit</artifactId>
            <version>${LATEST-VERSION}</version>
        </dependency>
```

And because this is not from maven central and only a hobby project.
You also need to tell maven where to look elsewhere for packages.

```
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://www.jitpack.io</url>
        </repository>
    </repositories>
```
