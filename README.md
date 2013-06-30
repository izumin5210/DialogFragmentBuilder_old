# DialogFragmentBuilder
## usage

    public class HogeFragment extends Fragment implements AlertDialogFragment.Callbacks {
        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            /*
               some code...
            */
            AlertDialogFragmentBuilder builder = new AlertDialogFragment.Builder();
            builder.setTitle("foo").setMessage("bar");
                    .setViewFromResource(R.layout.baz);
                    .setPositiveButtonLabel(R.string.label_ok);
                    .setNegativeButtonLabel(R.string.label_cancel);
                    .setTargetFragment(this, 0);
                    .create().show(getFragmentManager(), "alert_dialog");
            /*
               some code...
            */
        }

        /*
            some methods...
        */

        @Override
        public void onDialogClicked(DialogInterface dialog, int which) {
            // some code when a button in the dialog is clicked.
        }

        @Overide
        public void onDialogCanceled(DialogInterface dialog) {
            // some code when the dialog is canceled.
        }
    }

## version history
- 2013/06/30 fix bugs
- 2013/06/21 fisrt commit

## licence
Copyright (c) 2013 Masayuki IZUMI

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.