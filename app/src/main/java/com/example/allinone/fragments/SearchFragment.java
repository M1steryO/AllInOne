package com.example.allinone.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.allinone.R;
import com.example.allinone.social_networks_api.reddit.GetRedditPostAsyncTask;
import com.example.allinone.social_networks_api.youtube.GetVideoAsyncTask;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import org.w3c.dom.Text;

import java.util.Objects;


public class SearchFragment extends Fragment {

    private RecyclerView reddit_posts_list;
    private RecyclerView youtube_videos_list;
    private EditText search_text;
    private ScrollView scrollView;
    private String req;
    private ViewFlipper vf;
    private SortDialogFragment sortDialog;
    private TextView search_sort_btn;
    private String currentSort = "popular";

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        search_text = view.findViewById(R.id.search_text);

        req = (search_text.getText().toString());
        reddit_posts_list = view.findViewById(R.id.reddit_posts_list);
        youtube_videos_list = view.findViewById(R.id.youtube_videos_list);
        vf = view.findViewById(R.id.vf);
        RadioButton reddit_posts_btn = view.findViewById(R.id.reddit_posts_btn);

        reddit_posts_btn.setOnClickListener(radioButtonClickListener);

        RadioButton youtube_videos_btn = view.findViewById(R.id.youtube_videos_btn);
        youtube_videos_btn.setOnClickListener(radioButtonClickListener);


        search_sort_btn = view.findViewById(R.id.sort_btn);
        search_sort_btn.setOnClickListener(sort_btn_click_listener);

        sortDialog = new SortDialogFragment();


        search_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    req = (search_text.getText().toString());
                    if (req.length() > 1) {
                        reddit_posts_btn.setChecked(true);
                        vf.setDisplayedChild(0);
                        GetRedditPostAsyncTask get_reddit_post = new GetRedditPostAsyncTask(reddit_posts_list);
                        get_reddit_post.execute(req, currentSort, "false");
                        GetVideoAsyncTask get_videos = new GetVideoAsyncTask(youtube_videos_list, getLifecycle());
                        get_videos.execute(req, currentSort, "false");


                        handled = true;
                    }
                    else
                        showWarningMessage("Запрос должен быть больше 1 символа ! Повторите попытку ввода");
                }
                return handled;
            }
        });


        return view;
    }

    View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
        @SuppressLint({"ResourceAsColor", "NonConstantResourceId"})
        @Override
        public void onClick(View v) {
            boolean checked = ((RadioButton) v).isChecked();
            RadioButton rb = (RadioButton) v;
            switch (rb.getId()) {
                case R.id.reddit_posts_btn:
                    if (checked)

                        vf.setDisplayedChild(0);


                    break;
                case R.id.youtube_videos_btn:
                    if (checked)

                        vf.setDisplayedChild(1);


                    break;


                default:
                    break;
            }
        }
    };


    View.OnClickListener sort_btn_click_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            sortDialog.show(getChildFragmentManager(), "sort_dlg");
        }
    };

    public void changeSortBtnText(String s) {
        search_sort_btn.setText(s + " ▽");
    }

    public void sort_items(String s) {
        currentSort = s;
        GetRedditPostAsyncTask get_reddit_post = new GetRedditPostAsyncTask(reddit_posts_list);
        get_reddit_post.execute(search_text.getText().toString(), currentSort, "false");
        GetVideoAsyncTask get_videos = new GetVideoAsyncTask(youtube_videos_list, getLifecycle());
        get_videos.execute(search_text.getText().toString(), currentSort,"false");
    }

    private void showWarningMessage(String message) {
        DynamicToast.makeWarning(requireContext(), message, Toast.LENGTH_LONG).show();

    }
}


//get the image view
//        ImageView reddit_posts_btn = (ImageView) view.findViewById(R.id.reddit_posts_btn);
//
//        //set the ontouch listener
//        reddit_posts_btn.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (req.length() > 1) {
//
//                    GetRedditPostAsyncTask get_reddit_post = new GetRedditPostAsyncTask(reddit_posts_list);
//                    get_reddit_post.execute(search_text.getText().toString());
//                }
//
//                return true;
//            }
//        });
//        ImageView youtube_videos_btn = view.findViewById(R.id.youtube_videos_btn);
//
//        //set the ontouch listener
//        youtube_videos_btn.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (req.length() > 1) {
//
//                    GetVideoAsyncTask get_videos = new GetVideoAsyncTask(youtube_videos_list, getLifecycle());
//                    get_videos.execute(search_text.getText().toString());
//                    TextView title = view.findViewById(R.id.yt_list_title);
//                    title.setText("Видео по запросу - " + search_text.getText().toString());
//                }
//                return true;
//            }
//        });