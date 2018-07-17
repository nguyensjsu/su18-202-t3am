/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.time.Instant;

/**
 *
 * @author syle
 */
public class DateHelper {
    public static long getCurrentEpochTimestamp(){
        long now = Instant.now().toEpochMilli();
        return now;
    }
}
