package com.hxg.lib_hrouter.facade;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.hxg.lib_hrouter.HRouter;
import com.hxg.lib_hrouter.utils.SerializationUtil;

import java.io.Serializable;
import java.util.ArrayList;


public class Postcard extends RouteMeta {
    private Intent mIntent;
    private Context mContext;

    public Postcard(Context context, String path) {
        this.mContext = context;
        this.mPath = path;
        if (getDestination() != null) {
            this.mIntent = new Intent(mContext, getDestination());
        }
    }


    public Object navigation() {
        if (getDestination() != null) {
            HRouter.getInstance().navigation(mContext, mIntent, 0);
            return 1;
        }
        if (getFragment() != null) {
            try {
                return getFragment().newInstance();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }


    public Object navigation(Activity currentContext, int requestCode) {
        if (getDestination() != null) {
            HRouter.getInstance().navigation(currentContext, mIntent, requestCode);
            return 1;
        }
        return -1;
    }

    public Postcard with(Bundle bundle) {
        if (null != bundle && getDestination() != null) {
            mIntent.putExtras(bundle);
        }
        return this;
    }

    public Postcard withObject(String key, Object value) {
        if (getDestination() != null) {
            mIntent.putExtra(key, SerializationUtil.object2Json(value));
        }
        return this;
    }

    public Postcard withString(String key, String value) {
        if (getDestination() != null) {
            mIntent.putExtra(key, value);
        }
        return this;
    }

    public Postcard withBoolean(String key, boolean value) {
        if (getDestination() != null) {
            mIntent.putExtra(key, value);
        }
        return this;
    }

    public Postcard withShort(String key, short value) {
        if (getDestination() != null) {
            mIntent.putExtra(key, value);
        }
        return this;
    }

    public Postcard withInt(String key, int value) {
        if (getDestination() != null) {
            mIntent.putExtra(key, value);
        }
        return this;
    }

    public Postcard withLong(String key, long value) {
        if (getDestination() != null) {
            mIntent.putExtra(key, value);
        }
        return this;
    }

    public Postcard withDouble(String key, double value) {
        if (getDestination() != null) {
            mIntent.putExtra(key, value);
        }
        return this;
    }

    public Postcard withByte(String key, byte value) {
        if (getDestination() != null) {
            mIntent.putExtra(key, value);
        }
        return this;
    }

    public Postcard withChar(String key, char value) {
        if (getDestination() != null) {
            mIntent.putExtra(key, value);
        }
        return this;
    }

    public Postcard withFloat(String key, float value) {
        if (getDestination() != null) {
            mIntent.putExtra(key, value);
        }
        return this;
    }

    public Postcard withCharSequence(String key, CharSequence value) {
        if (getDestination() != null) {
            mIntent.putExtra(key, value);
        }
        return this;
    }

    public Postcard withParcelable(String key, Parcelable value) {
        if (getDestination() != null) {
            mIntent.putExtra(key, value);
        }
        return this;
    }

    public Postcard withParcelableArray(String key, Parcelable[] value) {
        if (getDestination() != null) {
            mIntent.putExtra(key, value);
        }
        return this;
    }

    public Postcard withParcelableArrayList(String key, ArrayList<? extends Parcelable> value) {
        if (getDestination() != null) {
            mIntent.putParcelableArrayListExtra(key, value);
        }
        return this;
    }

    public Postcard withIntegerArrayList(String key, ArrayList<Integer> value) {
        if (getDestination() != null) {
            mIntent.putIntegerArrayListExtra(key, value);
        }
        return this;
    }

    public Postcard withStringArrayList(String key, ArrayList<String> value) {
        if (getDestination() != null) {
            mIntent.putStringArrayListExtra(key, value);
        }
        return this;
    }

    public Postcard withCharSequenceArrayList(String key, ArrayList<CharSequence> value) {
        if (getDestination() != null) {
            mIntent.putCharSequenceArrayListExtra(key, value);
        }
        return this;
    }


    public Postcard withSerializable(String key, Serializable value) {
        if (getDestination() != null) {
            mIntent.putExtra(key, value);
        }
        return this;
    }


    public Postcard withByteArray(String key, byte[] value) {
        if (getDestination() != null) {
            mIntent.putExtra(key, value);
        }
        return this;
    }


    public Postcard withShortArray(String key, short[] value) {
        if (getDestination() != null) {
            mIntent.putExtra(key, value);
        }
        return this;
    }


    public Postcard withCharArray(String key, char[] value) {
        if (getDestination() != null) {
            mIntent.putExtra(key, value);
        }
        return this;
    }


    public Postcard withFloatArray(String key, float[] value) {
        if (getDestination() != null) {
            mIntent.putExtra(key, value);
        }
        return this;
    }


    public Postcard withCharSequenceArray(String key, CharSequence[] value) {
        if (getDestination() != null) {
            mIntent.putExtra(key, value);
        }
        return this;
    }


    public Postcard withBundle(String key, Bundle value) {
        if (getDestination() != null) {
            mIntent.putExtra(key, value);
        }
        return this;
    }

}
