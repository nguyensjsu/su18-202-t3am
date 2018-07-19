/*
 *
 * Class encapsulates the details of the authentication
 * Request
 * Hyunwook Shin
 *
 */

package model;

public class AuthRequest {

   String email;
   String password;
   boolean authenticated = false;

   public boolean authenticate(UserProfile user) {
      if ( password != null && password.equals( user.getPassword() ) ) {
         authenticated = true;
      } else {
         authenticated = false;
      }
      return authenticated;
   } 

   public boolean isAuthenticated() {
      return authenticated;
   }

   public String getEmail() {
      return email;
   }
}
