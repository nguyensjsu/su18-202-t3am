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

# While we use the unittest library, it is
# applied for testing end-to-end system.
# The test expects the backend server to
# be running in localhost listening to
# port 8202

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

   def testSignIn( self ):
      testemail =  "syle1@gmail.com"
      endpoint = "/api/v1/signin"
      url = URL.format( endpoint=endpoint, query="" )
      data = '{"email": ' + testemail + ', "password" :"password"}'
      response = requests.post( url, data=data )
      info = json.loads( response.text )
      assert 'user_id' in info
      assert 'balance' in info
      assert testemail == info[ 'email' ]
  

if __name__ == "__main__":
   unittest.main(argv=sys.argv[1:])
