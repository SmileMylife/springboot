/**
 * Created by ZhangPei on 2019/11/21.
 */
export function sendHttpPost(url, params) {
    let temp = document.createElement("form");
    temp.action = url;
    temp.method = "post";
    temp.style.display = "none";

    for (let x in params) {
        let opt = document.createElement("textarea");
        opt.name = x;
        opt.value = params[x];
        temp.appendChild(opt);
    }

    document.body.appendChild(temp);
    temp.submit();
    return temp;
}