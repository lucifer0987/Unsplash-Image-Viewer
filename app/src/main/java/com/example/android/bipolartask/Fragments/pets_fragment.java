package com.example.android.bipolartask.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bipolartask.Adapters.ImageAdapter;
import com.example.android.bipolartask.Models.Image;
import com.example.android.bipolartask.NetworkTasks.JsonParse;
import com.example.android.bipolartask.NetworkTasks.NetworkUtils;
import com.example.android.bipolartask.R;

import java.util.List;

public class pets_fragment extends Fragment   implements LoaderManager.LoaderCallbacks<List<Image>>{

    private RecyclerView rv_grid;
    private ImageAdapter mImageAdapter;
    private TextView tv_error;
    private ProgressBar mProgressBar;
    int loaderId = 23;
    String client_id = "";
    //Put your own unsplash client id here.
    private String STRING_URL =
            "https://api.unsplash.com/collections/139386/photos?client_id=" + client_id + "&&page=1&&per_page=30";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pets, container, false);

        tv_error = view.findViewById(R.id.tv_error_message_display);
        mProgressBar = view.findViewById(R.id.pb_loading_indicator);
        rv_grid = view.findViewById(R.id.rv_pets);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), GridLayoutManager.chooseSize(2, 2, 2));
        rv_grid.setLayoutManager(gridLayoutManager);
        rv_grid.setHasFixedSize(true);

        mImageAdapter = new ImageAdapter();
        rv_grid.setAdapter(mImageAdapter);

        loadingImages(STRING_URL);

        return view;
    }

    private void loadingImages(String url) {
        rv_grid.setVisibility(View.VISIBLE);
        tv_error.setVisibility(View.INVISIBLE);
        LoaderManager.LoaderCallbacks<List<Image>> callback = this;

        Bundle bundleForLoader = new Bundle();
        bundleForLoader.putString("url", url);

        getLoaderManager().initLoader(loaderId, bundleForLoader, callback);
    }

    @NonNull
    @Override
    public Loader<List<Image>> onCreateLoader(int id, @Nullable final Bundle args) {
        return new AsyncTaskLoader<List<Image>>(getContext()) {

            List<Image> mImageData = null;

            @Override
            protected void onStartLoading() {
                if (mImageData != null) {
                    deliverResult(mImageData);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    forceLoad();
                }
            }

            @Override
            public List<Image> loadInBackground() {
                try {
                    String jsonResponse = NetworkUtils.getJsonResponse(args.getString("url"));
                    return JsonParse.parse(jsonResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            public void deliverResult(List<Image> data) {
                mImageData = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Image>> loader, List<Image> data) {
        mProgressBar.setVisibility(View.INVISIBLE);

        if (data != null) {
            mImageAdapter.setImageData(data);
            rv_grid.setVisibility(View.VISIBLE);
            tv_error.setVisibility(View.INVISIBLE);
        }else{
            rv_grid.setVisibility(View.INVISIBLE);
            tv_error.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Image>> loader) {

    }

}
