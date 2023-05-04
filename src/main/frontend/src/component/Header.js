import react from 'react';
import styles from './css/main/header.css';

export default function Header( props ) {
    return (<>

        <a href="/"> HOME </a>
        <a href="/member/addmember"> 직원등록 </a>
        {/*이경석 링크용*/}
        <a href="/messenger"> 메신저 들어가기</a>

    </>);
}