import { useState, useEffect } from "react";
import axios from "axios";
function InstructorList(){
    const[instructors,setInstructors]=useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [selectedDepartment, setSelectedDepartment] = useState("הכל");
    useEffect(()=>{
        const fetchInstructor=async()=>{
            try{
                const response = await axios.get("http://localhost:8080/api/instructors");
                setInstructors(response.data);

            }catch(err){
                setError(err.message);
            }
            finally{
                setLoading(false);
            }
        };
        fetchInstructor();
    },[]);
    const departments = ["הכל", ...new Set(instructors.map(i => i.department))];
    const filteredinstructor=selectedDepartment === "הכל"
      ? instructors
      : instructors.filter(s => s.department === selectedDepartment);
      if (loading) {
    return (
      <div className="loading">
        <p>טוען מרצים...</p>
      </div>
    );
  }
  if (error) {
    return (
      <div className="error">
        <p>שגיאה בטעינת הנתונים: {error}</p>
        <button onClick={() => window.location.reload()}>נסה שוב</button>
      </div>
    );
  }
  return(
    <div className="instructor-list">
        <div className="header">
            <h2>instructor list</h2>
            <p className="count">סה״כ: {filteredinstructor.length} instructor</p>
        </div>
        <div className="filter">
            <label >filter by department</label>
            <select value={selectedDepartment} onChange={(e)=>setSelectedDepartment(e.target.value)}>
                {departments.map((dept) => (
            <option key={dept} value={dept}>{dept}</option>
          ))}
            </select>
        </div>
        {filteredinstructor.length === 0 ? (
        <p className="data-no">לא נמצאו סטודנטים במחלקה זו</p>
      ) : (
        <table>
            <thead>
                <tr>
                    <th>מספר</th>
              <th>שם מלא</th>
              <th>אימייל</th>
              <th>מחלקה</th>
              <th>title</th>
                </tr>
            </thead>
            <tbody>
                {filteredinstructor.map((instructors)=>(
                    <tr key={instructors.id}>
                        <td>{instructors.id}</td>
                        <td>{instructors.firstname} {instructors.lastname}</td>
                        <td>{instructors.email}</td>
                        <td>{instructors.department}</td>
                        <td>{instructors.title}</td>
                    </tr>
                ))}
            </tbody>
        </table>
      )}
    </div>
  );
}
export default InstructorList;