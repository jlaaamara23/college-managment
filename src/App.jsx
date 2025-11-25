import { useState } from "react";
import "./App.css";
import StudentList from "./components/StudentList";
import InstructorList from "./components/InstructorList";
import CourseList from "./components/CourseList";

function App() {
  const [activeTab, setActiveTab] = useState("students");

  return (
    <div className="App">
      <header className="App-header">
        <h1>מערכת ניהול מכללה</h1>
        <p>המכללה האקדמית נצרת</p>
      </header>

      <nav className="tabs">
        <button className={activeTab === "students" ? "active" : ""} onClick={() => setActiveTab("students")}>
          students
        </button>

        <button className={activeTab === "instructor" ? "active" : ""} onClick={() => setActiveTab("instructor")}>
          instructors
        </button>

        <button className={activeTab === "course" ? "active" : ""} onClick={() => setActiveTab("course")}>
          course
        </button>
      </nav>

      <main>
        {activeTab === "students" && <StudentList />}
        {activeTab === "instructor" && <InstructorList />}
        {activeTab === "course" && <CourseList />}
      </main>

      <footer>
        <p>© 2025 המכללה האקדמית נצרת. כל הזכויות שמורות.</p>
      </footer>
    </div>
  );
}

export default App;
