package edu.xtu.androidbase.weaher.ui.base;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by huilin on 2016/8/13.
 * Activity任务栈
 */
public class ActivityStack {

    private static ActivityStack activityStack;
    /**
     * 存储每个activity的栈
     */
    private Stack<Activity> stack;

    private ActivityStack(){
        stack = new Stack<>();
    }

    public static ActivityStack getInstanct(){
        if(activityStack==null){
            activityStack = new ActivityStack();
        }
        return activityStack;
    }

    public void add(Activity activity){
        if(activity!=null){
            stack.add(activity);
        }
    }

    public void remove(Activity activity){
        if(activity!=null){
            stack.remove(activity);
        }
    }

    public Stack<Activity> copyStack(){
        Stack<Activity> stackCopy = new Stack<>();
        if(stack!=null){
            stackCopy.addAll(stack);
        }
        return stackCopy;
    }

    public void finishAll(){
        Activity[] activities = new Activity[stack.size()];
        stack.copyInto(activities);
        for (Activity activity: activities){
            activity.finish();
        }
        stack.clear();
    }

    /**
     * 根据activity来保存
     * @param exceptActivity
     */
    public void finishAllActivityExcept(Activity exceptActivity){
        boolean isExist = false;
        Activity[] activities = new Activity[stack.size()];
        for (Activity activity: activities){
            if(activity==exceptActivity){
                isExist = true;
            }else {
                activity.finish();
            }
        }
        stack.clear();
        if(isExist){
            stack.add(exceptActivity);
        }
    }

    /**
     * 根据类名留存
     * @param exceptClass
     */
    public void finishAllActivityExceptClass(Class<? extends Activity> exceptClass){
        Activity exceptActivity = null;
        Activity[] activities = new Activity[stack.size()];
        stack.copyInto(activities);
        for (Activity activity : activities){
            if(activity.getClass()==exceptClass){
                exceptActivity = activity;
            }else {
                activity.finish();
            }
        }
        stack.clear();
        if(exceptActivity!=null){
            stack.add(exceptActivity);
        }
    }

    public Activity getTopActivity(){
        return stack.lastElement();
    }

    public int getActivitySize(){
        return stack.size();
    }

}
