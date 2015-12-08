package com.trial.leftshift.deezenotrials;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nvanbenschoten.motion.ParallaxImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new ParallaxFragment())
                    .commit();
        }
    }

    /**
     * A fragment containing a simple parallax image view
     */
    public static class ParallaxFragment extends Fragment {

        private ParallaxImageView mBackground;


        private boolean mParallaxSet = true;


        public ParallaxFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_parallax, container, false);
            if (rootView == null) return null;

            mBackground = (ParallaxImageView) rootView.findViewById(android.R.id.background);

            setCurrentImage();

            return rootView;
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            mBackground.setParallaxIntensity(1f + ((float) 15) / 40);
        }

        @Override
        public void onResume() {
            super.onResume();

            if (mParallaxSet) {
                mBackground.registerSensorManager();
            }
        }

        @Override
        public void onPause() {
            mBackground.unregisterSensorManager();
            super.onPause();
        }


        private void setCurrentImage() {

            mBackground.setImageDrawable(getResources().getDrawable(R.drawable.splash1));

        }

    }

}
