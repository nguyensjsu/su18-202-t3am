/*
 *
 * Class encapsulates the details of the authentication
 * Request
 * Hyunwook Shin
 *
 */

package model;

public class AuthRequest {

   String uid;
   String password;
   boolean authenticated = false;

   public boolean authenticate(UserProfile user) {
      if ( password.equals( user.getPassword() ) ) {
         authenticated = true;
      } else {
         authenticated = false;
      }
      return authenticated;
   } 

   public boolean isAuthenticated() {
      return authenticated;
   }

   public String getUserId() {
      return uid;
   }
}
