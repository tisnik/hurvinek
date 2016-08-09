# hurvinek

FIXME: description

## Installation

Download from http://example.com/FIXME.

## Usage

FIXME: explanation

    $ java -jar hurvinek-0.1.0-standalone.jar [args]



## Options

Current version of Hurvinek accepts only one command line option used to specify port
on which Hurvinek should accepts all HTTP request.

Usage:
-p     port number
--port port number



## REST API

### List of all products

Get list of all products from the database:

    $ curl hostname:port/api/products

### List of chapters for given product

    $ curl hostname:port/api/chapters?product-id=_PRODUCT_ID_

### List of groups for given chapter

    $ curl hostname:port/api/groups?chapter-id=_CHAPTER_ID_

### List of components for given chapter

    $ curl hostname:port/api/components?chapter-id=_CHAPTER_ID_

### Component to chapter mapping for selected product

    $ curl hostname:port/api/components-to-chapter?product-id=_PRODUCT_ID_
    $ curl hostname:port/api/components-to-chapter?product-name=_PRODUCT_NAME_

## Output format selection

It is possible to select output format for given data:

1. JSON (JavaScript Object Notation)
2. EDN (Extensible Data Notation)
3. Plain text
4. XML
5. CSV (Comma Separated Values)

To select output format, use the following parameter: **format**:

Examples:

    $ curl "hostname:port/api/components?chapter-id=1&format=xml"
    $ curl "hostname:port/api/components?chapter-id=1&format=json"
    $ curl "hostname:port/api/components?chapter-id=1&format=csv"
    $ curl "hostname:port/api/components?chapter-id=1&format=txt"
    $ curl "hostname:port/api/components?chapter-id=1&format=text"
    $ curl "hostname:port/api/components?chapter-id=1&format=edn"

Default is JSON output (it does not need to be specified).

## Bugs

None I know about :-)


### Any Other Sections
### That You Think
### Might be Useful

## License

Copyright © 2016 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
