package com.nonexistentware.igor.batterystatus;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * Created by igor on 4/1/17.
 */

public class NotificationSFX {

    private static SoundPool soundPool;
    private static int damage;
    private static int pickup;
    private static int gamestart;
    private static int endofthegame;



    public NotificationSFX(Context context) {

        /*SoundPool (int maxStreams,
        int streamType,
        int scrQuality)**/
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);


        /**damage = soundPool.load(context, R.raw.damage, 1);
        endofthegame = soundPool.load(context, R.raw.endofthegame, 1);
        gamestart = soundPool.load(context, R.raw.gamestart, 1);
        pickup = soundPool.load(context, R.raw.pickup, 1);
*/

    }

    public void playdamagesound() {
        //play.(int soundID, float leftVolume,  float rightVolume, int priority, int loop, float rate)
        soundPool.play(damage, 1.0f, 1.0f, 1, 0, 1.0f);
    }
}
