/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.UUID;

/**
 *
 * @author syle
 */
public class UUIDHelper {
    public static String getRandomUUID(){
        final UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
