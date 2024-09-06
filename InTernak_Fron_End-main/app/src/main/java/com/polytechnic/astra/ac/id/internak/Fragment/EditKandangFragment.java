package com.polytechnic.astra.ac.id.internak.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.polytechnic.astra.ac.id.internak.API.VO.HewanVO;
import com.polytechnic.astra.ac.id.internak.API.VO.KandangVO;
import com.polytechnic.astra.ac.id.internak.R;
import com.polytechnic.astra.ac.id.internak.ViewModel.HewanViewModel;
import com.polytechnic.astra.ac.id.internak.ViewModel.KandangViewModel;

public class EditKandangFragment extends Fragment {
    private static final String TAG = "EditKandangFragment";
    private static final String ARG_KANDANG_ID = "kandang_id";
    private static final String ARG_KANDANG_NAMA = "kandang_nama";
    private static final String ARG_KANDANG_JENIS = "kandang_jenis";
    private static final String ARG_KANDANG_KAPASITAS = "kandang_kapasitas";
    private static final String ARG_KANDANG_LUAS = "kandang_luas";
    private static final String ARG_KANDANG_ALAMAT = "kandang_alamat";
    private static final String ARG_KANDANG_SUHU = "kandang_suhu";
    private static final String ARG_KANDANG_LATTITUDE = "kandang_lattitude";
    private static final String ARG_KANDANG_LONGTITUDE = "kandang_longtitude";
    private static final String ARG_KANDANG_STATUS = "kandang_status";

    private int kandangId;
    private String kandangNama;
    private String kandangJenis;
    private int kandangKapasitas;
    private int kandangLuas;
    private String kandangAlamat;
    private double kandangLattitude;
    private double kandangLongtitude;
    private int kandangSuhu;
    private String kandangStatus;

    private EditText namaKandangEditText;
    private Spinner jenisKandangSpinner;
    private EditText kapasitasKandangEditText;
    private EditText luasKandangEditText;
    private EditText alamatKandangEditText;
    private EditText suhuKandangEditText;
    private KandangViewModel kandangViewModel;
    private String[] jenisKandangArray = {"Peranakan", "Pembesaran", "Karantina"};
    private Button btSimpan;

    public EditKandangFragment() {

    }

    public static EditKandangFragment newInstance(int kandangId, String kandangNama, String kandangJenis, int kandangKapasitas, int kandangLuas, String kandangAlamat
            , double kandangLattitude, double kandangLongtitude, int kandangSuhu, String kandangStatus) {
        EditKandangFragment fragment = new EditKandangFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_KANDANG_ID, kandangId);
        args.putString(ARG_KANDANG_NAMA, kandangNama);
        args.putString(ARG_KANDANG_JENIS, kandangJenis);
        args.putInt(ARG_KANDANG_KAPASITAS, kandangKapasitas);
        args.putInt(ARG_KANDANG_LUAS, kandangLuas);
        args.putString(ARG_KANDANG_ALAMAT, kandangAlamat);
        args.putDouble(ARG_KANDANG_LATTITUDE, kandangLattitude);
        args.putDouble(ARG_KANDANG_LONGTITUDE, kandangLongtitude);
        args.putInt(ARG_KANDANG_SUHU, kandangSuhu);
        args.putString(ARG_KANDANG_STATUS, kandangStatus);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            kandangId = getArguments().getInt(ARG_KANDANG_ID);
            kandangNama = getArguments().getString(ARG_KANDANG_NAMA);
            kandangJenis = getArguments().getString(ARG_KANDANG_JENIS);
            kandangKapasitas = getArguments().getInt(ARG_KANDANG_KAPASITAS);
            kandangLuas = getArguments().getInt(ARG_KANDANG_LUAS);
            kandangAlamat = getArguments().getString(ARG_KANDANG_ALAMAT);
            kandangLattitude = getArguments().getDouble(ARG_KANDANG_LATTITUDE);
            kandangLongtitude = getArguments().getDouble(ARG_KANDANG_LONGTITUDE);
            kandangAlamat = getArguments().getString(ARG_KANDANG_ALAMAT);
            kandangSuhu = getArguments().getInt(ARG_KANDANG_SUHU);
            kandangStatus = getArguments().getString(ARG_KANDANG_STATUS);
        }
        kandangViewModel = new ViewModelProvider(this).get(KandangViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_kandang, container, false);

        namaKandangEditText = view.findViewById(R.id.nama_kandang);
        jenisKandangSpinner = view.findViewById(R.id.jenis_kandang);
        kapasitasKandangEditText = view.findViewById(R.id.kapasitas_kandang);
        luasKandangEditText = view.findViewById(R.id.luas_kandang);
        alamatKandangEditText = view.findViewById(R.id.alamat_kandang);
        suhuKandangEditText = view.findViewById(R.id.suhu_kandang);
        btSimpan = view.findViewById(R.id.fabAddKandang);

        namaKandangEditText.setText(kandangNama);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, jenisKandangArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jenisKandangSpinner.setAdapter(adapter);
        kapasitasKandangEditText.setText(String.valueOf(kandangKapasitas));
        luasKandangEditText.setText(String.valueOf(kandangLuas));
        suhuKandangEditText.setText(String.valueOf(kandangSuhu));
        alamatKandangEditText.setText(kandangAlamat);

        btSimpan.setOnClickListener(v -> simpanData());
        ImageButton btnBack = view.findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        return view;
    }

    private void simpanData() {
        String namaBaru = namaKandangEditText.getText().toString();
        String jenisBaru = jenisKandangSpinner.getSelectedItem().toString();
        int kapasitasBaru = Integer.parseInt(kapasitasKandangEditText.getText().toString());
        int luasBaru = Integer.parseInt(luasKandangEditText.getText().toString());
        String alamatBaru = alamatKandangEditText.getText().toString();
        int suhuBaru = Integer.parseInt(suhuKandangEditText.getText().toString());

        // Lakukan validasi dan simpan data baru
        if (namaBaru.isEmpty() || jenisBaru.isEmpty() || kapasitasBaru <= 0 || luasBaru <= 0 || alamatBaru.isEmpty() || suhuBaru <= 0) {
            Toast.makeText(getContext(), "Data tidak valid. Harap periksa kembali.", Toast.LENGTH_SHORT).show();
            return;
        }

        KandangVO kandangBaru = new KandangVO();
        kandangBaru.setKdgId(kandangId);
        kandangBaru.setKdgNama(namaBaru);
        kandangBaru.setKdgJenis(jenisBaru);
        kandangBaru.setKdgKapasitas(kapasitasBaru);
        kandangBaru.setKdgLuas(luasBaru);
        kandangBaru.setKdgAlamat(alamatBaru);
        kandangBaru.setKdgLattitude(kandangLattitude);
        kandangBaru.setKdgLongtitude(kandangLongtitude);
        kandangBaru.setKdgSuhu(suhuBaru);
        kandangBaru.setKdgStatus(kandangStatus);

        kandangViewModel.updateKandang(kandangBaru);

        Toast.makeText(getContext(), "Data berhasil disimpan.", Toast.LENGTH_SHORT).show();
        navigateToNextActivity();
        namaKandangEditText.setText("");
        kapasitasKandangEditText.setText("");
        luasKandangEditText.setText("");
        alamatKandangEditText.setText("");
        suhuKandangEditText.setText("");
    }

    private void navigateToNextActivity() {
        try {
            KandangFragment kandangFragment = new KandangFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_login, kandangFragment)
                    .addToBackStack(null)
                    .commit();
        } catch (Exception e) {
            Log.e(TAG, "Error starting EditKandangFragment", e);
        }
    }
}