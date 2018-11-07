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
  1: "#7E7E7E",
  2: "#FAAD14",
  3: "#016FFF",
  4: "#56C61F",
  5: "#F5222D"
}
export {statusClassName, statuslogo, statusBgColor, statusBorderColor};
