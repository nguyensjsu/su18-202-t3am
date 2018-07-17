#!/usr/bin/env python

import unittest
import json
import requests

PORT = 8202
URL = 'http://localhost:%d{endpoint}{query}' % PORT

def checkResponse( response ):
   return response.ok

class TestCardAPI( unittest.TestCase ):

   def testGetCards( self ):
      endpoint = "/api/v1/cards"
      url = URL.format( endpoint=endpoint, query="uid=" )
      assert checkResponse( requests.post( url ) )

if __name__ == "__main__":
   unittest.main(argv=sys.argv[1:])
