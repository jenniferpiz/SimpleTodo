# Pre-work - SimpleTodo

SimpleTodo is an android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item.

Submitted by: Jennifer Godinez

Time spent: 11 hours spent in total

## User Stories

The following **required** functionality is completed:

*[x] User can **successfully add and remove items** from the todo list

*[x] User can **tap a todo item in the list and bring up an edit screen for the todo item** and then have any changes to the text reflected in the todo list.

*[x] User can **persist todo items** and retrieve them properly on app restart

The following **optional** features are implemented:

*[x] Persist the todo items [into SQLite](http://guides.codepath.com/android/Persisting-Data-to-the-Device#sqlite) instead of a text file

*[x] Improve style of the todo items in the list [using a custom adapter] (http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView)

*[ ] Add support for completion due dates for todo items (and display within listview item)

*[ ] Use a [DialogFragment](http://guides.codepath.com/android/Using-DialogFragment) instead of new Activity for editing items

*[ ] Add support for selecting the priority of each todo item (and display in listview item)

*[x] Tweak the style improving the UI / UX, play with colors, images or backgrounds

The following **additional** features are implemented:

*[ ] List anything else that you can get done to improve the app functionality!

## Video Walkthrough

Here's a walkthrough of implemented user stories:

![Simple Todo](http://imgur.com/rq0gqUI.gif "Jennifer's Simple Todo")

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Project Analysis

As part of your pre-work submission, please reflect on the app and answer the following questions below:

**Question 1:** "What are your reactions to the Android app development platform so far? Compare and contrast Android's approach to layouts and user interfaces in past platforms you've used."

**Answer:** 
Everything is pre-built so it is easier to design UI.  The layout can be tricky though because it needs to be able to support different android versions.   My previous experience had to deal with support of display to different OS and has to be backward compatible but no tool to see everything in one screen unlike Android Studio where it can display selected androids side by side.


**Question 2:** "Take a moment to reflect on the `ArrayAdapter` used in your pre-work. How would you describe an adapter in this context and what is its function in Android? Why do you think the adapter is important? Explain the purpose of the `convertView` in the `getView` method of the `ArrayAdapter`."

**Answer:**
I think Adapter is used for displaying data in Android view.  convertView is used to re-populate existing views therefore optimizing performance.

## Notes

**Question** Describe any challenges encountered while building the app.

**Answer:**
ConstraintLayout as the IDE default, wrong positioning at first try.   Understanding Adapter. Formatting of README file.

## License

    Copyright [2017] [Jennifer Godinez]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.




