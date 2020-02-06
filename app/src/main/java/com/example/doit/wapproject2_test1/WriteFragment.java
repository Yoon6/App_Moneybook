package com.example.doit.wapproject2_test1;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.doit.wapproject2_test1.dao.ConsmDAO;
import com.example.doit.wapproject2_test1.db.ConsumeDAO;
import com.example.doit.wapproject2_test1.entity.ConsumeEntity;
import com.example.doit.wapproject2_test1.model.Consume;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class WriteFragment extends Fragment implements View.OnClickListener {
    Spinner writeCategoryList;

    Button submitBtn;
    Button cancelBtn;

    RadioButton writeRadioButton1;
    RadioButton writeRadioButton2;
    EditText writePlace;
    EditText writeDate;
    EditText writeCost;

    String radio_state;

    AppDatabase db;

    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd", Locale.KOREAN);

    DatePickerDialog datePickerDialog;
    Calendar dateCalendar;

    Consume consume = null;
    private ConsumeDAO consumeDAO;
    private AddConsmTask task;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        consumeDAO = new ConsumeDAO(getActivity());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_write, container, false);

        findViewsById(v);

        db = AppDatabase.getAppDatabase(getActivity());

        // 스피너
        final String [] values =
                {"식비/생활", "패션/미용", "교통/통신", "문화/여가", "교육/학습", "의료/건강"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        writeCategoryList.setAdapter(adapter);

        setListeners();

        //For orientation change.
        if (savedInstanceState != null) {
            dateCalendar = Calendar.getInstance();
            if (savedInstanceState.getLong("dateCalendar") != 0)
                dateCalendar.setTime(new Date(savedInstanceState.getLong("dateCalendar")));
        }

        return v;
    }

    private void setListeners() {
        writeDate.setOnClickListener(this);
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        dateCalendar = Calendar.getInstance();
                        dateCalendar.set(year, monthOfYear, dayOfMonth);
                        writeDate.setText(formatter.format(dateCalendar
                                .getTime()));
                    }

                }, newCalendar.get(Calendar.YEAR),
                newCalendar.get(Calendar.MONTH),
                newCalendar.get(Calendar.DAY_OF_MONTH));

        submitBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
    }

    // 프래그먼트 새로고침
    private void fg_refresh() {
        writeRadioButton1.setChecked(true);
        writePlace.setText(null);
        writeCost.setText(null);
        writeDate.setText(null);
        writeCategoryList.setSelection(0);
    }

    @Override
    public void onResume() {
        //getActivity().setTitle(R.string.add_emp);
        //getActivity().getActionBar().setTitle(R.string.add_emp);
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (dateCalendar != null)
            outState.putLong("dateCalendar", dateCalendar.getTime().getTime());
    }

    private void findViewsById(View v) {

        writeRadioButton1=v.findViewById(R.id.writeRadioButton1);
        writeRadioButton2=v.findViewById(R.id.writeRadioButton2);

        writeCategoryList = v.findViewById(R.id.writeCategory);
        writePlace = v.findViewById(R.id.writePlace);
        writeDate=v.findViewById(R.id.writeDate);
        writeDate.setInputType(InputType.TYPE_NULL);
        writeCost = v.findViewById(R.id.writeCost);

        submitBtn = v.findViewById(R.id.submitBtn);
        cancelBtn = v.findViewById(R.id.cancelBtn);
    }

    @Override
    public void onClick(View view) {
        if (view == writeDate) {
            datePickerDialog.show();
        } else if (view == submitBtn) {
            submitMsg();
        } else if (view == cancelBtn) {
            cancelMsg();
        }
    }

    private void setConsume() {
        consume = new Consume();

        if(writeRadioButton1.isChecked()==true){
            radio_state="expense";
        }else{
            radio_state="income";
        }

        consume.setState(radio_state);
        consume.setCategory(writeCategoryList.getSelectedItem().toString());
        consume.setPlace(writePlace.getText().toString());
        consume.setCost(writeCost.getText().toString());
        if (dateCalendar != null)
            consume.setDate(dateCalendar.getTime());

    }

    public class AddConsmTask extends AsyncTask<Void, Void, Long> {

        private final WeakReference<Activity> activityWeakRef;

        public AddConsmTask(Activity context) {
            this.activityWeakRef = new WeakReference<Activity>(context);
        }

        @Override
        protected Long doInBackground(Void... arg0) {
            long result = consumeDAO.save(consume);
            return result;
        }

        @Override
        protected void onPostExecute(Long result) {
            if (activityWeakRef.get() != null
                    && !activityWeakRef.get().isFinishing()) {
                if (result != -1)
                    Toast.makeText(activityWeakRef.get(), "완료!", Toast.LENGTH_LONG).show();
            }
        }
    }

    // 취소 경고창
    public void cancelMsg() {
        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(getActivity());
        alert_confirm.setMessage("작성내용이 삭제됩니다.").setCancelable(false).setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 'YES'
                        fg_refresh();
                    }
                }).setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 'No'
                        return;
                    }
                });
        AlertDialog alert = alert_confirm.create();
        alert.show();

    }

    // 입력 확인창
    public void submitMsg() {
        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(getActivity());
        alert_confirm.setMessage("작성내용을 입력합니다.").setCancelable(false).setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 'YES'
                        //setConsume();
//
                        //task = new AddConsmTask(getActivity());
                        //task.execute((Void) null);

                        if(Integer.parseInt(writeCost.getText().toString()) != 0) {
                            Toast.makeText(getActivity(), "한글자 이상입력해주세요.", Toast.LENGTH_SHORT).show();
                        }else{
                            new InsertAsyncTask(db.consmDAO()).execute(new ConsumeEntity());

                        }

                        fg_refresh();
                    }
                }).setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 'No'
                        return;
                    }
                });
        AlertDialog alert = alert_confirm.create();
        alert.show();

    }

    //메인스레드에서 데이터베이스에 접근할 수 없으므로 AsyncTask를 사용하도록 한다.
    public static class InsertAsyncTask extends AsyncTask<ConsumeEntity, Void, Void> {
        public ConsmDAO consmDAO;

        public  InsertAsyncTask(ConsmDAO consmDAO){
            this.consmDAO = consmDAO;
        }

        @Override //백그라운드작업(메인스레드 X)
        protected Void doInBackground(ConsumeEntity... consms) {
            //추가만하고 따로 SELECT문을 안해도 라이브데이터로 인해
            //getAll()이 반응해서 데이터를 갱신해서 보여줄 것이다,  메인액티비티에 옵저버에 쓴 코드가 실행된다. (라이브데이터는 스스로 백그라운드로 처리해준다.)
            ConsmDAO.insert(consms[0]);
            return null;
        }
    }
}