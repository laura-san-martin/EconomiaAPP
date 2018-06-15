package com.example.laura.economia;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AparelhoView.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AparelhoView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AparelhoView extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Obj_Aparelho aparelho;

    private OnFragmentInteractionListener mListener;

    public AparelhoView(){}

    @SuppressLint("ValidFragment")
    public AparelhoView(Obj_Aparelho aparelho) {
        this.aparelho = aparelho;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AparelhoView.
     */
    // TODO: Rename and change types and number of parameters
    public static AparelhoView newInstance(String param1, String param2) {
        AparelhoView fragment = new AparelhoView();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        if(aparelho != null)
        {
            ((TextView)getView().findViewById(R.id.aparelhoNome)).setText((aparelho.nome));
            ((TextView)getView().findViewById(R.id.aparelhoTipo)).setText((aparelho.tipo));
            ((TextView)getView().findViewById(R.id.aparelhoVoltagem)).setText((aparelho.voltagem));
            ((TextView)getView().findViewById(R.id.aparelhoPotencia)).setText((aparelho.potencia.toString()));
            ((TextView)getView().findViewById(R.id.aparelhoPotencia)).setText((aparelho.potencia_em_standby.toString()));

            ((TextView)getView().findViewById(R.id.aparelhoView_btn)).setText("Atualizar");
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_aparelho_novo, container, false);
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
