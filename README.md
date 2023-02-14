
# School System

School System That Tracks Class, Subects, Time Table And More..


## API Reference

#### Get Number Of Subjects Per Grade

```http
  GET /api/v1/schools/grades/:gradeId/subjects/:subjectId
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `gradeId` | `long` | **Required**. Grade ID |
| `subjectId` | `long` | **Required**. Subject ID |

#### Get All Classess For Specific Subject And Time

```http
  Post /api/v1/schools/classes/times
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `timeTableRequest`      | `obj` | **Required**. Contains ( subjectId,schoolId,requestDateTime) |


