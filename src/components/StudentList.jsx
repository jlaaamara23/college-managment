import { useState, useEffect } from "react";
import axios from "axios";

function StudentList() {
  const [students, setStudents] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const [firstname, setFirstname] = useState("");
  const [lastname, setLastname] = useState("");
  const [email, setEmail] = useState("");
  const [department, setDepartment] = useState("");
  const [gpa, setGpa] = useState("");
  const [year, setYear] = useState("");

  const [selectedDepartment, setSelectedDepartment] = useState("הכל");

  const fetchStudents = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/students");
      setStudents(response.data);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchStudents();
  }, []);

  const handleSubmit = async () => {
    try {
      await axios.post("http://localhost:8080/api/students", {
        firstname: firstname,
        lastname: lastname,
        email: email,
        department: department,
        gpa: gpa,
        year: year
      });

      fetchStudents();

      setFirstname("");
      setLastname("");
      setEmail("");
      setDepartment("");
      setGpa("");
      setYear("");
    } catch (error) {
      alert("Error creating student");
    }
  };
  const handleDelete = async (id) => {
  try {
    await axios.delete(`http://localhost:8080/api/students/${id}`);
    setStudents(students.filter(s => s.id !== id));
  } catch (err) {
    setError(err.message);
  }
};

    
  const filteredStudents =
    selectedDepartment === "הכל"
      ? students
      : students.filter((s) => s.department === selectedDepartment);

  const departments = ["הכל", ...new Set(students.map((s) => s.department))];

  if (loading) return <p>טוען סטודנטים...</p>;
  if (error) return <p>שגיאה: {error}</p>;

  return (
    <div className="student-list">

      <h2>הוספת סטודנט</h2>
      <div>
        <input placeholder="שם פרטי" value={firstname} onChange={(e) => setFirstname(e.target.value)} />
        <input placeholder="שם משפחה" value={lastname} onChange={(e) => setLastname(e.target.value)} />
        <input placeholder="אימייל" value={email} onChange={(e) => setEmail(e.target.value)} />
        <input placeholder="מחלקה" value={department} onChange={(e) => setDepartment(e.target.value)} />
        <input placeholder="GPA" value={gpa} onChange={(e) => setGpa(e.target.value)} />
        <input placeholder="שנה" value={year} onChange={(e) => setYear(e.target.value)} />
        <button onClick={handleSubmit}>הוסף סטודנט</button>
      </div>

      <hr />

      <h2>רשימת סטודנטים</h2>
      <select value={selectedDepartment} onChange={(e) => setSelectedDepartment(e.target.value)}>
        {departments.map((dept) => (
          <option key={dept} value={dept}>{dept}</option>
        ))}
      </select>

      <table>
        <thead>
          <tr>
            <th>שם מלא</th>
            <th>אימייל</th>
            <th>מחלקה</th>
            <th>GPA</th>
            <th>שנה</th>
          </tr>
        </thead>
        <tbody>
          {filteredStudents.map((student) => (
            <tr key={student.id}>
              
              <td>{student.firstname} {student.lastname}</td>
              <td>{student.email}</td>
              <td>{student.department}</td>
              <td>{student.gpa}</td>
              <td>{student.year}</td>
              <td>
        <button onClick={() => handleDelete(student.id)}>
          Delete
        </button>
      </td>
            </tr>
          ))}
        </tbody>
        
      </table>

    </div>
  );
}

export default StudentList;
