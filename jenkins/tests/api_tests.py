#!/usr/bin/env python

import unittest
import json
import sys
import requests

PORT = 8202
URL = 'http://localhost:%d{endpoint}{query}' % PORT
TEST_UID = '958bbc20-cda2-4f57-9fe6-e1ecb6e6e913'

def checkResponse( response ):
   return response.ok

class TestAPIs( unittest.TestCase ):

   def testGetCards( self ):
      endpoint = "/api/v1/cards"
      url = URL.format( endpoint=endpoint, query="?uid=%s" % TEST_UID )
      response = requests.get( url )
      for entry in json.loads( response.text ):
         assert 'uid' in entry
         assert 'date_added' in entry
         assert len( entry[ 'code' ] ) == 3
         assert len( entry[ 'number' ] ) == 9
      assert checkResponse( response )


if __name__ == "__main__":
   unittest.main(argv=sys.argv[1:])
