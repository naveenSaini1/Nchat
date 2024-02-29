import { IoIosSearch } from "react-icons/io";
import styles from "./css/TopSearch.module.css"
import { useContext, useRef } from "react";
import { UserContext } from "../../../contextapi/UserContext";
const TopSearch=()=>{
    const {addFriendToUser}=useContext(UserContext);
    const emailRef=useRef();
    const hadnledAddFreindBtn=()=>{
        addFriendToUser(emailRef.current.value);
        emailRef.current.value="";
    }
    return (
        <div className={styles.searchContainer}>
            
            
            <div className={styles.searchInput}>
            <input ref={emailRef} type="email" name="email" id="email" />
            </div>
            <div className={styles.searchIcon}>
            <button onClick={hadnledAddFreindBtn}  style={{width:"100%",color:"green",cursor:"pointer"}}>Add friends</button>
            </div>

        </div>

    )
}
export default TopSearch;