package com.kevinmost.leddit.module;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import com.kevinmost.leddit.App;
import dagger.Module;
import dagger.Provides;
import net.dean.jraw.RedditClient;
import net.dean.jraw.http.UserAgent;

import javax.inject.Singleton;

@Module
public class AppModule {
    private static final String TAG = AppModule.class.getSimpleName();

    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    App provideApp() {
        return App.get();
    }

    @Provides
    @Singleton
    RedditClient provideRedditClient() {
        int versionNumber = 0;
        try {
            versionNumber = context.getPackageManager()
                                   .getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Could not find package", e);
        }
        final UserAgent userAgent = UserAgent.of("android",
                context.getPackageName(),
                String.valueOf(versionNumber),
                "shadowdude777");

        final RedditClient client = new RedditClient(userAgent);

        return client;
    }
}
