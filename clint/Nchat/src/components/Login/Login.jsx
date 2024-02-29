import { useContext, useRef } from "react";
import styles from "./Login.module.css"
import { UserContext } from "../../contextapi/UserContext";
const Login=()=>{
    const {loginUser} =useContext(UserContext);
    const emailIdRef=useRef();
    const passwordRef=useRef();

    const handledLogin=()=>{
        let object={
            emailId:emailIdRef.current.value,
            password:passwordRef.current.value
        }
        emailIdRef.current.value="";
        passwordRef.current.value="";
        loginUser(object);

    }
    return(
        <div className={styles.bodyContainer}>
        <div className={styles.centerCardContainer}>
            <div className={styles.marginTopBottom}>
                <p>Enter Email</p>
                <input ref={emailIdRef} className={styles.txtInput} type="email" name="email" id="email" placeholder="Enter Email" />
            </div>
            <div className={styles.marginTopBottom}>
                <p>Enter Password</p>
                <input ref={passwordRef} className={styles.txtInput} type="password" name="passsword" id="password" placeholder="Enter Password"/>
            </div>
            <button  onClick={handledLogin} className={styles.loginBtn}>Login</button>
            <div>
                <p style={{margin:"10px 0px", textAlign:"center" }}>dont have Account <span style={{color:"red"}}>Register</span></p>
            </div>
        </div>
        </div>
    )

}
export default Login;