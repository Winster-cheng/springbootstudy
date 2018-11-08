import success from '@/assets/icon_status_success.svg';
import fail from '@/assets/icon_status_fail.svg';
import running from '@/assets/icon_status_running.svg';
import stop from '@/assets/icon_status_stop.svg';
import waitting from '@/assets/icon_status_waitting.svg';

const statusClassName = {
  1: 'stop',
  2: 'waitting',
  3: 'running',
  4: 'success',
  5: 'fail',
};
const statuslogo = {
  1: stop,
  2: waitting,
  3: running,
  4: success,
  5: fail,
};

const statusBgColor = {
  1: '#8E8E8E',
  2: '#FFF6E5',
  3: '#E5F0FF',
  4: '#ECFFE3',
  5: '#FFE8EA',
};

const statusBorderColor = {
  1: "rgba(126,126,126,.5)",
  2: "rgba(250,173,20,.5)",
  3: "rgba(1,111,255,.5)",
  4: "rgba(86,198,31,.5)",
  5: "rgba(245,34,45,.5)"
}
export {statusClassName, statuslogo, statusBgColor, statusBorderColor};
