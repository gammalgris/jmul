
Coding Styleguide
=================

General
-------

See http://www.oracle.com/technetwork/java/codeconvtoc-136057.html

Deviations, exceptions and aspects of importance are provided below.

Variable Names
--------------

Variables (i.e. local variables or class members) are named according to the java
convention. No prefixes of any kind are enforced.

Examples:

```java
    public String createText() {
   	   ...
       String text = "...";
   	   ...
    }
```

```java
    public class TextBlock {
   	   ...
       private String block;
   	   ...
    }
```

Parameter Names
---------------

Parameter names start with an indefinite article (e.g. a or an) or an indefinite quantity
(e.g. some).

Examples:

```java
    public void processText(String aText) {
       ...
    }
```

```java
    public void processTextBlocks(String... someTextBlocks) {
       ...
    }
```

Other prefixes like in the hungarian notation are not necessary.

The names should be in such a way that a reader of the code can infer what content
a variable or parmeter contains. Using abbreviations should be avoided for purposes of
legibility. An exception to this rule is if the abbreviation is a commonly used
expression.

Parameter Processing
--------------------

Parameters should in general not be modified. If a computation produces some output
then return the output as new object.

Example:

```java
    public List<String> processNames(List<String> someNames) {
       ...
    }
```

Marking the parameters as final is an option for marking parameters that should
not be modified, but this will have no impact on objects (i.e. the reference cannot
be changed but the content can).

If in special cases a modification is necessary (e.g. to avoid the instantiation and
initialization of a complex return object) then the parameter has to be marked with
the annotation @Modified.

Example:

```java
    public void processNames(@Modified List<String> someNames) {
       ...
    }
```

By convention, parameters that are not marked with the annotation should not be
modified.
There is no guarantee that parameters are not modified or that this convention
might be misleading if not applied and updated properly. But sticking to this
convention should make it easier to identify the cases when a parameter is
actually modified.
