import { useState, useEffect } from "react";
import axios from "axios";

function CourseList() {
  const [courses, setCourses] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const [selectedName, setSelectedName] = useState("all");
  const [name, setName] = useState("");
  const [courseCode, setCourseCode] = useState("");
  const [credits, setCredits] = useState("");
  const [instructorId, setInstructorId] = useState("");
  const [editingId, setEditingId] = useState(null);

  const fetchCourses = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/courses");
      setCourses(response.data);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchCourses();
  }, []);

  const handleSubmit = async () => {
    try {
      await axios.post("http://localhost:8080/api/courses", {
        name: name,
        courseCode: courseCode,
        credits: Number(credits),
        instructorId: Number(instructorId)
      });

      fetchCourses();

      setName("");
      setCourseCode("");
      setCredits("");
      setInstructorId("");
    } catch (error) {
      alert("Error creating course");
    }
  };
  const handleDelete = async (id)=>{
    try {
    await axios.delete(`http://localhost:8080/api/courses/${id}`);
    setCourses(courses.filter(s => s.id !== id));
    }
    catch (err) {
    setError(err.message);
  }
  };
  const handleUpdate = async () => {
  try {
    await axios.put(`http://localhost:8080/api/courses/${editingId}`, {
      name: name,
      courseCode: courseCode,
      credits: Number(credits),
      instructorId: Number(instructorId)
    });

    fetchCourses();

    setEditingId(null);
    setName("");
    setCourseCode("");
    setCredits("");
    setInstructorId("");

  } catch (err) {
    setError(err.message);
  }
};

  const names = ["all", ...new Set(courses.map((c) => c.name))];

  const filteredCourses =
    selectedName === "all"
      ? courses
      : courses.filter((c) => c.name === selectedName);

  if (loading) {
    return (
      <div className="loading">
        <p>טוען קורסים...</p>
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

  return (
    <div className="course-list">
      <div className="header">
        <input placeholder="name" value={name} onChange={(e) => setName(e.target.value)} />
        <input placeholder="courseCode" value={courseCode} onChange={(e) => setCourseCode(e.target.value)} />
        <input placeholder="credits" value={credits} onChange={(e) => setCredits(e.target.value)} />
        <input placeholder="instructor Id" value={instructorId} onChange={(e) => setInstructorId(e.target.value)} />
        <button onClick={editingId ? handleUpdate : handleSubmit}>
  {editingId ? "Update Course" : "Add Course"}
</button>

        <p className="count">סה"כ {filteredCourses.length} קורסים</p>
      </div>

      <div className="filter">
        <label>סינון לפי שם:</label>
        <select
          value={selectedName}
          onChange={(e) => setSelectedName(e.target.value)}
        >
          {names.map((n) => (
            <option key={n} value={n}>
              {n}
            </option>
          ))}
        </select>
      </div>

      {filteredCourses.length === 0 ? (
        <p className="data-no">לא נמצאו קורסים תואמים</p>
      ) : (
        <table>
          <thead>
            <tr>
              
              <th>שם</th>
              <th>קוד</th>
              <th>נק"ז</th>
              <th>מרצה</th>
              
            </tr>
          </thead>
          <tbody>
            {filteredCourses.map((course) => (
              <tr key={course.id}>
                <td>{course.name}</td>
                <td>{course.courseCode}</td>
                <td>{course.credits}</td>
                <td>{course.instructorId}</td>
                <td><button className="btn" onClick={()=>handleDelete(course.id)}>delete</button></td>
                <td>
                  <button className="btn"
  onClick={() => {
    setEditingId(course.id)
    setName(course.name);
    setCourseCode(course.courseCode);
    setCredits(course.credits);
    setInstructorId(course.instructorId);
  }}
>
  Edit
</button>

                  
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default CourseList;
