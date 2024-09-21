"use client"
import {useEffect, useState} from 'react';
import {Input, Button, Card, Spacer, Textarea, Divider, Listbox, ListboxItem} from '@nextui-org/react';
import {CardBody} from "@nextui-org/card";
import {createItem, getTodoList, updateTodoItem} from "@/api/todoList";

const TodoList = () => {
  const [tasks, setTasks] = useState<TodoItem[]>([]);
  const [newTask, setNewTask] = useState<string>("");

  const addTask = () => {
    if (newTask.trim() !== '') {
        const t = {
            id: "",
            description: newTask,
            done: false
        };
        createItem(t).then(
            (res) => {
                t.id = res.data.id;
                setTasks([...tasks, t]);
                setNewTask("");
            }
        );
    }
  };
  const getAllTasks =() => {
      getTodoList().then(
          (res) => {
              setTasks(res.data.data);
          });
  }
  useEffect(() => {
      getAllTasks();
  }, [])
  const toggleTaskCompletion  = (id: any) => {
      const updatedTasks = tasks.filter((task) => task.id+"" === id);
      if (updatedTasks.length == 1) {
          const update = updatedTasks[0];
          update.done = !update.done;
          updateTodoItem(update.id, update).then(
                (res) => {
                    getAllTasks();
                }
          )
      }
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
                          hidden={item.done}
                          textValue = "123"
                          style={{
                                  display: 'flex',
                                  justifyContent: 'space-between',
                                  alignItems: 'center',
                                  marginBottom: '10px',
                                  textDecoration: item.done ? 'line-through' : 'none',
                                  color: item.done ? '#888' : 'inherit',
                              }}
                      >

                          <Card>
                              <CardBody>
                                  {item.description}
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
