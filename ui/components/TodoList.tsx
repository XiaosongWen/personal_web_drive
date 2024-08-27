"use client"
import { useState } from 'react';
import {Input, Button, Card, Spacer, Textarea, Divider, Listbox, ListboxItem} from '@nextui-org/react';
import { nanoid } from 'nanoid';
import {CardBody} from "@nextui-org/card";
interface Task {
    id: string;
    name: string;
    completed: boolean;
}

const defaultTask = () => {
    return {
        id: nanoid(6),
        name: "",
        completed: false,
    }
};
const TodoList = () => {
  const [tasks, setTasks] = useState<Task[]>([]);
  const [newTask, setNewTask] = useState<string>("");

  const addTask = () => {
    if (newTask.trim() !== '') {
        const t = defaultTask();
        t.name = newTask;
        setTasks([...tasks, t]);
        setNewTask("");
    }
  };
  const toggleTaskCompletion  = (id: any) => {
      const updatedTasks = tasks.map((task) =>
          task.id === id ? { ...task, completed: !task.completed } : task
      );
      setTasks(updatedTasks);
  };
  return (
      <>
          <h1>To-Do List</h1>
          <Listbox
              items={tasks}
              aria-label="Dynamic Actions"
              onAction={(key) => toggleTaskCompletion (key)}
          >
                  {(item) => (
                      <ListboxItem
                          key={item.id}
                          hidden={item.completed}
                          textValue = "123"
                          style={{
                                  display: 'flex',
                                  justifyContent: 'space-between',
                                  alignItems: 'center',
                                  marginBottom: '10px',
                                  textDecoration: item.completed ? 'line-through' : 'none',
                                  color: item.completed ? '#888' : 'inherit',
                              }}
                      >

                          <Card>
                              <CardBody>
                                  {item.name}
                              </CardBody>
                          </Card>

                      </ListboxItem>
                  )}
          </Listbox>
          <Divider className="my-1" />
          <Input
            placeholder="Add a new task"
            value={newTask}
            onChange={(e) => setNewTask(e.target.value)}
            fullWidth
            isClearable
          />
          <Spacer y={2} />
          <Button onPress={addTask} radius="full" className="bg-gradient-to-tr from-pink-500 to-yellow-500 text-white shadow-lg">
              Add Task
          </Button>

          {/*<ul>*/}
          {/*  {tasks.map((task, index) => (*/}
          {/*    <li key={index} style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '10px' }}>*/}
          {/*      <Textarea>{task.name}</Textarea>*/}
          {/*      <Button color="warning" onPress={() => removeTask(task.id)}>*/}
          {/*        Remove*/}
          {/*      </Button>*/}
          {/*    </li>*/}
          {/*  ))}*/}
          {/*</ul>*/}
      </>
  );
};

export default TodoList;
