package com.bambamsingh.newsdemo.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bambamsingh.newsdemo.BuildConfig;
import com.bambamsingh.newsdemo.adapters.MostPopularListAdapter;
import com.bambamsingh.newsdemo.R;
import com.bambamsingh.newsdemo.activities.MostPopularDetailActivity;
import com.bambamsingh.newsdemo.base.BaseFragment;
import com.bambamsingh.newsdemo.models.Response;
import com.bambamsingh.newsdemo.utilites.MostPopularDataRequest;
import com.bambamsingh.newsdemo.utilites.RecyclerviewItemDecoration;
import com.bambamsingh.newsdemo.utilites.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import io.reactivex.disposables.Disposable;

public class MostPopularListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = MostPopularListFragment.class.getSimpleName();

    @Nullable
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    private RecyclerView recyclerView;

    @Nullable
    private MostPopularListAdapter mostPopularListAdapter;

    @Nullable
    private View errorLayout;

    @Nullable
    private Disposable disposable;

    @Nullable
    private MostPopularDataRequest mostPopularDataRequest;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this._context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_most_popular_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        onRefresh();
    }

    private void initViews(@NonNull View view) {
        recyclerView = view.findViewById(R.id.xRecyclerView);

        mostPopularListAdapter = new MostPopularListAdapter(_context, newsItem -> MostPopularDetailActivity.start(_context, newsItem));
        recyclerView.setAdapter(mostPopularListAdapter);

        recyclerView.addItemDecoration(new RecyclerviewItemDecoration(getResources().getDimensionPixelSize(R.dimen.spacing_micro)));
        recyclerView.setLayoutManager(new LinearLayoutManager(_context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        swipeRefreshLayout = view.findViewById(R.id.xSwipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);

        errorLayout = view.findViewById(R.id.xErrorLayout);
        Button buttonRetry = view.findViewById(R.id.xButtonRetry);

        buttonRetry.setOnClickListener(v -> onRefresh());

        initRequest();
    }

    private void initRequest() {
        if (mostPopularDataRequest == null) {
            mostPopularDataRequest = new MostPopularDataRequest();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mostPopularListAdapter = null;
        swipeRefreshLayout = null;
        recyclerView = null;
    }

    @Override
    public void onRefresh() {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onRefresh() called");
        }
        showProgress(true);

        initRequest();

        disposable = mostPopularDataRequest.getMostPopularArticles()
                .subscribe(this::updateItems,
                        this::handleError);
    }

    private void showProgress(boolean shouldShow) {
        swipeRefreshLayout.setRefreshing(shouldShow);
        Utils.setVisible(recyclerView, !shouldShow);
        Utils.setVisible(errorLayout, !shouldShow);
    }

    private void updateItems(@Nullable Response response) {
        if (mostPopularListAdapter != null)
            mostPopularListAdapter.replaceItems(response.getResults());

        Utils.setVisible(recyclerView, true);
        swipeRefreshLayout.setRefreshing(false);
        Utils.setVisible(errorLayout, false);
    }

    private void handleError(Throwable th) {
        if (Utils.isDebug()) {
            Log.e(TAG, th.getMessage(), th);
        }
        Utils.setVisible(errorLayout, true);
        swipeRefreshLayout.setRefreshing(false);
        Utils.setVisible(recyclerView, false);
    }

    @Override
    public void onStop() {
        super.onStop();
        showProgress(false);
        Utils.disposeSafe(disposable);
        disposable = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mostPopularListAdapter != null && mostPopularListAdapter.getItemCount() > 0) {
            Utils.setVisible(errorLayout, false);
        }
    }
}