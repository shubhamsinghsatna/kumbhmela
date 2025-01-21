package com.mahakumbh.dishanirdesh.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mahakumbh.dishanirdesh.R;
import com.mahakumbh.dishanirdesh.activity.DashboardActivity;
import com.mahakumbh.dishanirdesh.database.SharedPrefs;
import com.mahakumbh.dishanirdesh.fragment.More.AnnouncementsFragment;
import com.mahakumbh.dishanirdesh.fragment.More.BooksOnMahaKumbhFragment;
import com.mahakumbh.dishanirdesh.fragment.More.DoesAndDontsFragment;
import com.mahakumbh.dishanirdesh.fragment.More.FAQFragment;

import java.util.Locale;


public class MoreFragment extends Fragment {
    private View Root;
    private TextView tv_current_language;
    private CardView cv_languageChooser;
    RelativeLayout rl_doAnddonts,rl_announcements,rl_books,rl_faq;


    private String[] languages = {"English", "हिंदी"};
    private String[] languageCodes = {"en", "hi"}; // Add more language codes as needed


SharedPrefs sharedPrefs;

    public static MoreFragment newInstance() {
        MoreFragment fragment = new MoreFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPrefs = new SharedPrefs(requireContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Root= inflater.inflate(R.layout.fragment_more, container, false);


        initView();
        return Root;
    }


    private void initView(){

        tv_current_language= Root.findViewById(R.id.tv_current_language);
        updateLanguageUI(sharedPrefs.getAppLanguage());
        cv_languageChooser= Root.findViewById(R.id.cv_languageChooser);
        cv_languageChooser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLanguageDialog();
            }
        });


        rl_doAnddonts = Root.findViewById(R.id .rl_doAnddonts);
        rl_doAnddonts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(DoesAndDontsFragment.newInstance());
            }
        });

        rl_announcements = Root.findViewById(R.id .rl_announcements);
        rl_announcements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(AnnouncementsFragment.newInstance());
            }
        });

        rl_books = Root.findViewById(R.id .rl_books);
        rl_books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(BooksOnMahaKumbhFragment.newInstance());
            }
        });

        rl_faq = Root.findViewById(R.id .rl_faq);
        rl_faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(FAQFragment.newInstance());
            }
        });
    }


    private void openFragment(Fragment fragment){
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(fragment.getTag())
                .commit();
    }


    private void showLanguageDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Choose Language");
        builder.setItems(languages, (dialog, which) -> {
            String selectedLanguage = languageCodes[which];

            if(selectedLanguage.equals(sharedPrefs.getAppLanguage())){
                return;
            }
            sharedPrefs.setAppLanguage(selectedLanguage);
            updateLanguageUI(selectedLanguage);
            switchLanguage(selectedLanguage);
        });
        builder.show();
    }

    private void switchLanguage(String languageCode) {

        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        Intent intent = new Intent(requireActivity(), DashboardActivity.class);
        intent.putExtra("default","more");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        requireActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out); // Apply animation
        requireActivity().finish();
    }



    private void updateLanguageUI(String languageCode) {
        int index = java.util.Arrays.asList(languageCodes).indexOf(languageCode);
        String languageName = (index != -1) ? languages[index] : "English";
        tv_current_language.setText(languageName);
    }
}