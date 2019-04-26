package com.bambamsingh.newsdemo.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bambamsingh.newsdemo.R;
import com.bambamsingh.newsdemo.base.BaseAppCompatActivity;
import com.bambamsingh.newsdemo.models.MostPopular;
import com.bambamsingh.newsdemo.utilites.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.browser.customtabs.CustomTabsIntent;

public class MostPopularDetailActivity extends BaseAppCompatActivity {

    private static final String TAG = MostPopularDetailActivity.class.getSimpleName();
    private static final String EXTRA_NEWS_ITEM = "extra:mostPopularObj";
    private CustomTabsIntent.Builder builder;
    private CustomTabsIntent customTabsIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_most_popular_detail);

        final MostPopular mostPopular = (MostPopular) getIntent().getSerializableExtra(EXTRA_NEWS_ITEM);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(mostPopular.getSection());

        final ImageView imageViewTop = findViewById(R.id.xImageViewTop);
        final TextView textViewTitle = findViewById(R.id.xTextViewTitle);
        final TextView textViewDate = findViewById(R.id.xTextViewDate);
        final TextView textViewDescription = findViewById(R.id.xTextViewDescription);
        final TextView textViewBy = findViewById(R.id.xTextViewBy);
        final TextView textViewReadMore = findViewById(R.id.xTextViewReadMore);

        Glide.with(this)
                .load(mostPopular.getMedia().get(0).getMediaMetaData().get(0).getUrl())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageViewTop);

        textViewTitle.setText(mostPopular.getTitle());
        textViewBy.setText(mostPopular.getByline());
        textViewDate.setText(mostPopular.getPublishedDate());
        textViewDescription.setText(mostPopular.getAbs());

        textViewReadMore.setOnClickListener(v -> {
            if (builder == null) {
                builder = new CustomTabsIntent.Builder();
            }
            builder.setToolbarColor(getResources().getColor(R.color.colorPrimary));
            if (customTabsIntent == null) {
                customTabsIntent = builder.build();
            }
            customTabsIntent.launchUrl(MostPopularDetailActivity.this, Uri.parse(mostPopular.getUrl()));
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public static void start(@NonNull Context context, @NonNull MostPopular mostPopular) {

        if (Utils.isDebug()) {
            Log.d(TAG, "mostPopular: " + mostPopular);
        }

        context.startActivity(new Intent(context, MostPopularDetailActivity.class).putExtra(EXTRA_NEWS_ITEM, mostPopular));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}