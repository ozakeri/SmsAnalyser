package gap.com.smsapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import gap.com.smsapplication.R;
import gap.com.smsapplication.Result;
import gap.com.smsapplication.utils.PersianDate;
import gap.com.smsapplication.utils.Util;

public class SmsListAdapter extends RecyclerView.Adapter<SmsListAdapter.CustomView> {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd- HH:mm");
    private List<Result> resultList = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;
    private String bankName;

    public SmsListAdapter(List<Result> resultList, String bankName) {
        this.resultList = resultList;
        this.bankName = bankName;
    }

    @NonNull
    @Override
    public CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new CustomView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomView holder, int position) {

        Result result = resultList.get(position);

        if (result != null) {

            if (result.getBankName() != null) {
                holder.txt_bankName.setText(result.getBankName());
            }
            if (result.getDeposit() != null) {
                holder.txt_deposit.setText(result.getDeposit());
            }
            if (result.getHarvest() != null) {
                holder.txt_harvest.setText(result.getHarvest());
            }
            if (result.getRemaining() != null) {
                holder.txt_remaining.setText(result.getRemaining());
            }
            if (result.getDate() != null) {
                String hour = null;
                String minute = null;
                Date expireDate = null;
                try {
                    expireDate = simpleDateFormat.parse(Util.ConvertMilliSecondsToFormattedDate(result.getDate()));
                    if (expireDate != null) {
                        PersianDate persianDate = new PersianDate(expireDate);

                        if (persianDate.getHour() < 10) {
                            hour = "0" + persianDate.getHour();
                        } else {
                            hour = String.valueOf(persianDate.getHour());
                        }

                        if (persianDate.getMinute() < 10) {
                            minute = "0" + persianDate.getMinute();
                        } else {
                            minute = String.valueOf(persianDate.getMinute());
                        }

                        //  holder.txt_date.setText(persianDate.getShYear()+"/" + persianDate.getShMonth() +"/" +  persianDate.getShDay());
                        holder.txt_date.setText(persianDate.dayName() + " " + persianDate.getShDay() + " " + persianDate.monthName() + " " +
                                persianDate.getShYear() + " ساعت " + hour + ":" + minute);
                    }


                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }


            if (result.getBankName() == null) {
                holder.layout_bank.setVisibility(View.GONE);
            }
            if (result.getDeposit() == null) {
                holder.layout_deposit.setVisibility(View.GONE);
            }
            if (result.getHarvest() == null) {
                holder.layout_harvest.setVisibility(View.GONE);
            }
            if (result.getRemaining() == null) {
                holder.layout_remaining.setVisibility(View.GONE);
            }

            if (result.getBankName().equals("صادرات")) {
                holder.bank_icon.setBackgroundResource(R.drawable.saderat_icon);
            }
            if (result.getBankName().equals("قوامین")) {
                holder.bank_icon.setBackgroundResource(R.drawable.qavamin_icon);
            }
            if (result.getBankName().equals("رسالت")) {
                holder.bank_icon.setBackgroundResource(R.drawable.resalat_icon);
            }
            if (result.getBankName().equals("سپه")) {
                holder.bank_icon.setBackgroundResource(R.drawable.sepah_icon);
            }
            if (result.getBankName().equals("پاسارگاد")) {
                holder.bank_icon.setBackgroundResource(R.drawable.pasargad);
            }
            if (result.getBankName().equals("ملي")) {
                holder.bank_icon.setBackgroundResource(R.drawable.melli);
            }
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public static class CustomView extends RecyclerView.ViewHolder {

        TextView txt_bankName, txt_deposit, txt_harvest, txt_remaining, txt_date;
        LinearLayout layout_bank, layout_deposit, layout_harvest, layout_remaining;
        ImageView bank_icon;

        public CustomView(@NonNull View itemView) {
            super(itemView);
            txt_bankName = itemView.findViewById(R.id.txt_bankName);
            txt_deposit = itemView.findViewById(R.id.txt_deposit);
            txt_harvest = itemView.findViewById(R.id.txt_harvest);
            txt_remaining = itemView.findViewById(R.id.txt_remaining);
            layout_bank = itemView.findViewById(R.id.layout_bank);
            layout_deposit = itemView.findViewById(R.id.layout_deposit);
            layout_harvest = itemView.findViewById(R.id.layout_harvest);
            layout_remaining = itemView.findViewById(R.id.layout_remaining);
            bank_icon = itemView.findViewById(R.id.bank_icon);
            txt_date = itemView.findViewById(R.id.txt_date);
        }
    }
}
